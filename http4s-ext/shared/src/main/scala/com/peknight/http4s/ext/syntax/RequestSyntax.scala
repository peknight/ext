package com.peknight.http4s.ext.syntax

import com.comcast.ip4s.IpAddress
import com.peknight.http4s.ext.syntax.headers.getXForwardedFor
import org.http4s.Request

trait RequestSyntax:
  extension [F[_]] (request: Request[F])
    def getRemoteAddr: Option[IpAddress] = request.headers.getXForwardedFor.headOption.orElse(request.remoteAddr)
  end extension
end RequestSyntax
object RequestSyntax extends RequestSyntax
