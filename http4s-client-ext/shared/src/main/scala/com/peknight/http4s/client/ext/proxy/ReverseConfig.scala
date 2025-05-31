package com.peknight.http4s.client.ext.proxy

import com.comcast.ip4s.{Host, Port}
import com.peknight.http4s.ext.syntax.uri.withAuthority
import org.http4s.Uri

trait ReverseConfig:
  def host: Host
  def port: Option[Port]
  def subdomain: String

  def uriMapper: PartialFunction[Uri, Uri] =
    case uri if uri.host.exists(_.value.contains(subdomain)) =>
      uri.withAuthority(org.http4s.Uri.Host.fromIp4sHost(host), port)
end ReverseConfig
