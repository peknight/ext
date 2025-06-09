package com.peknight.http4s.ext.uri

import org.http4s.Uri
import org.http4s.Uri.Host

package object host:
  def fromString(value: String): Option[Host] = com.comcast.ip4s.Host.fromString(value).map(Host.fromIp4sHost)
  def toIp4sHost(host: Host): Option[com.comcast.ip4s.Host] =
    host match
      case regName: Uri.RegName => regName.toHostname
      case host => host.toIpAddress
end host
