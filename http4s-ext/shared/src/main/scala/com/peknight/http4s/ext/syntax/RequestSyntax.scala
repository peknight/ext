package com.peknight.http4s.ext.syntax

import cats.syntax.option.*
import com.comcast.ip4s.{IpAddress, Port}
import com.peknight.http4s.ext.syntax.headers.{getXForwardedFor, getXRealIP}
import com.peknight.http4s.ext.syntax.uri.withAuthority
import com.peknight.http4s.ext.uri.host.fromString
import org.http4s.headers.{Forwarded, Host}
import org.http4s.{Request, Uri}

trait RequestSyntax:
  extension [F[_]] (request: Request[F])
    def getRemoteAddr: Option[IpAddress] =
      request.headers.getXForwardedFor.headOption
        .orElse(request.headers.get[Forwarded].flatMap(_.values.head.maybeFor).map(_.nodeName).flatMap {
          case Forwarded.Node.Name.Ipv4(address) => address.some
          case Forwarded.Node.Name.Ipv6(address) => address.some
          case _ => none[IpAddress]
        })
        .orElse(request.headers.getXRealIP)
        .orElse(request.remoteAddr)

    def getUri: Uri =
      request.headers.get[Host]
        .flatMap(host => fromString(host.host)
          .map(uriHost => request.uri.withAuthority(uriHost, host.port.flatMap(Port.fromInt), replacePort = true)))
        .getOrElse(request.uri)
  end extension
end RequestSyntax
object RequestSyntax extends RequestSyntax
