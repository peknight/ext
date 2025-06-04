package com.peknight.http4s.client.ext.proxy

import cats.effect.MonadCancel
import cats.syntax.functor.*
import com.comcast.ip4s.Port
import com.peknight.http4s.ext.syntax.uri.withAuthority
import com.peknight.http4s.ext.uri.host.fromString
import org.http4s.client.Client
import org.http4s.headers.*
import org.http4s.{HttpRoutes, Request, Uri}

trait ReverseProxy:
  def routes[F[_]](client: Client[F])(f: PartialFunction[Uri, Uri])
                  (using MonadCancel[F, Throwable])
  : HttpRoutes[F] =
    HttpRoutes.of[F] {
      case req if f.isDefinedAt(getOriginUri[F](req)) =>
        val originUri = getOriginUri[F](req)
        val targetUri = f(originUri)
        val request = req.withUri(targetUri)
          .putHeaders(targetUri.host.map(host => Host(host.value, targetUri.authority.flatMap(_.port))))
          .putHeaders(req.headers.get[Referer].mapUri(f)(_.uri)((referrer, uri) => referrer.copy(uri = uri)))
          .putHeaders(req.headers.get[`X-Forwarded-For`]
            .map(xForwardedFor => xForwardedFor.copy(values = xForwardedFor.values.append(req.remoteAddr)))
            .getOrElse(`X-Forwarded-For`(req.remoteAddr))
          )
          .putHeaders(originUri.scheme.map(`X-Forwarded-Proto`.apply).orElse(req.headers.get[`X-Forwarded-Proto`]))
          .removeHeader[Connection]
          .removeHeader[`Keep-Alive`]
          .removeHeader[`Proxy-Authenticate`]
          .removeHeader[`Proxy-Authorization`]
          .removeHeader[Upgrade]
        client.run(request).allocated.map((resp, release) => resp
          .putHeaders(resp.headers.get[`Content-Location`].mapUri(f)(_.uri)((location, uri) => location.copy(uri = uri)))
          .putHeaders(resp.headers.get[Location].mapUri(f)(_.uri)((location, uri) => location.copy(uri = uri)))
          .withEntity(resp.body.onFinalize(release))
        )
    }

  private def getOriginUri[F[_]](req: Request[F]): Uri =
    req.headers.get[Host]
      .flatMap(host => fromString(host.host)
        .map(uriHost => req.uri.withAuthority(uriHost, host.port.flatMap(Port.fromInt), replacePort = true)))
      .getOrElse(req.uri)

  extension [A] (option: Option[A])
    def mapUri(f: PartialFunction[Uri, Uri])(get: A => Uri)(update: (A, Uri) => A): Option[A] = option.map { a =>
      val origin = get(a)
      if f.isDefinedAt(origin) then update(a, f(origin)) else a
    }
  end extension
end ReverseProxy
object ReverseProxy extends ReverseProxy