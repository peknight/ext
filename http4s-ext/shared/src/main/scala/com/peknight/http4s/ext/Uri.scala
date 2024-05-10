package com.peknight.http4s.ext

import cats.parse.Parser0
import org.http4s.UriOps

import java.nio.charset.StandardCharsets

object Uri:
  def parser: Parser0[org.http4s.Uri] =
    UriOps.ParserOps.absoluteUri(StandardCharsets.ISO_8859_1)
      .orElse(UriOps.ParserOps.relativeRef(StandardCharsets.ISO_8859_1))
end Uri
