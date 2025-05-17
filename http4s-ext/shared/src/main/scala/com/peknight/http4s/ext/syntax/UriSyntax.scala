package com.peknight.http4s.ext.syntax

import com.comcast.ip4s.Port
import org.http4s.Uri

trait UriSyntax:
  extension (uri: Uri)
    def withAuthority(host: Uri.Host, port: Option[Port] = None, userInfo: Option[Uri.UserInfo] = None,
                      replacePort: Boolean = false, replaceUserInfo: Boolean = false): Uri =
      val nextPort: Option[Int] = (port, uri.authority.flatMap(_.port), replacePort) match
        case (Some(port), _, _) => Some(port.value)
        case (_, _, true) => None
        case (_, origin, _) => origin
      val nextUserInfo: Option[Uri.UserInfo] = (userInfo, uri.authority.flatMap(_.userInfo), replaceUserInfo) match
        case (Some(userInfo), _, _) => Some(userInfo)
        case (_, _, true) => None
        case (_, origin, _) => origin
      uri.copy(authority = Some(Uri.Authority(nextUserInfo, host, nextPort)))
  end extension
end UriSyntax
object UriSyntax extends UriSyntax
