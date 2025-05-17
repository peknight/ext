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
          .putHeaders(targetUri.host.map(host => Host(host.value)))
          .putHeaders(req.headers.get[Referer].map { referrer =>
            if f.isDefinedAt(referrer.uri) then referrer.copy(uri = f(referrer.uri)) else referrer
          })
          .removeHeader[Connection]
          .removeHeader[`Keep-Alive`]
          .removeHeader[`Proxy-Authenticate`]
          .removeHeader[`Proxy-Authorization`]
          .removeHeader[Upgrade]
        client.run(request).allocated.map((resp, release) => resp.withEntity(resp.body.onFinalize(release)))
    }

  private def getOriginUri[F[_]](req: Request[F]): Uri =
    req.headers.get[Host]
      .flatMap(host => fromString(host.host)
        .map(uriHost => req.uri.withAuthority(uriHost, host.port.flatMap(Port.fromInt), replacePort = true)))
      .getOrElse(req.uri)
end ReverseProxy
object ReverseProxy extends ReverseProxy