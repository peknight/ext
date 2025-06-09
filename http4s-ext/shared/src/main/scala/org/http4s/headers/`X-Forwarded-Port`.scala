package org.http4s.headers

import cats.parse.Parser0
import com.comcast.ip4s.Port
import org.http4s.internal.parsing.Rfc3986
import org.http4s.{Header, ParseResult, ParseResultOps}
import org.typelevel.ci.CIStringSyntax

final case class `X-Forwarded-Port`(port: Port)
object `X-Forwarded-Port`:
  def parse(s: String): ParseResult[`X-Forwarded-Port`] =
    ParseResultOps.fromParser(parser, "Invalid Replay-Nonce header")(s)
  private val parser: Parser0[`X-Forwarded-Port`] =
    Rfc3986.digit.rep.string.mapFilter { s =>
      Port.fromString(s).map(apply)
    }
  given headerInstance: Header[`X-Forwarded-Port`, Header.Single] =
    Header.create(ci"X-Forwarded-Port", v => v.port.toString, parse)
end `X-Forwarded-Port`