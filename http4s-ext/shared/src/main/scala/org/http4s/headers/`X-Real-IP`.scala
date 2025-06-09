package org.http4s.headers

import cats.parse.Parser0
import com.comcast.ip4s.IpAddress
import org.http4s.internal.parsing.Rfc3986
import org.http4s.{Header, ParseResult, ParseResultOps}
import org.typelevel.ci.CIStringSyntax

final case class `X-Real-IP`(ipAddress: IpAddress)
object `X-Real-IP`:
  def parse(s: String): ParseResult[`X-Real-IP`] =
    ParseResultOps.fromParser(parser, "Invalid X-Real-IP header")(s)
  private val parser: Parser0[`X-Real-IP`] =
    (Rfc3986.ipv4Address.backtrack | Rfc3986.ipv6Address).map(apply)
  given headerInstance: Header[`X-Real-IP`, Header.Single] =
    Header.create(ci"X-Real-IP", v => v.ipAddress.toString, parse)
end `X-Real-IP`