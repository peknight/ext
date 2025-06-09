package org.http4s.headers

import cats.parse.Parser
import org.http4s.internal.parsing.Rfc3986
import org.http4s.util.{Renderable, Writer}
import org.http4s.{Header, Uri}

import scala.util.Try

object `X-Forwarded-Host` extends HeaderCompanion[`X-Forwarded-Host`]("X-Forwarded-Host"):
  def apply(host: String, port: Int): `X-Forwarded-Host` = apply(host, Some(port))

  private[http4s] val parser =
    val port = Parser.string(":") *> Rfc3986.digit.rep.string.mapFilter { s =>
      Try(s.toInt).toOption
    }
    (Uri.Parser.host ~ port.?).map { case (host, port) =>
      `X-Forwarded-Host`(host.value, port)
    }

  implicit val headerInstance: Header[`X-Forwarded-Host`, Header.Single] =
    createRendered { h =>
      new Renderable {
        def render(writer: Writer): writer.type = {
          writer.append(h.host)
          if (h.port.isDefined) writer << ':' << h.port.get
          writer
        }
      }
    }
end `X-Forwarded-Host`
final case class `X-Forwarded-Host`(host: String, port: Option[Int] = None)
