package com.peknight.http4s.ext.uri

import org.http4s.Uri.Host

package object host:
  def fromString(value: String): Option[Host] = com.comcast.ip4s.Host.fromString(value).map(Host.fromIp4sHost)
end host
