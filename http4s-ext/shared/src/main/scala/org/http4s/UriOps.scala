package org.http4s

import cats.parse.{Parser, Parser0}

import java.nio.charset.Charset

object UriOps:
  object ParserOps:
    def absoluteUri(cs: Charset): Parser[Uri] = Uri.Parser.absoluteUri(cs)
    def relativeRef(cs: Charset): Parser0[Uri] = Uri.Parser.relativeRef(cs)
  end ParserOps
end UriOps
