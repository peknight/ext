package com.peknight.circe.parser.ext

import io.circe.{Decoder, Error}

object ParserOps:
  def decode[A: Decoder](input: String): Either[Error, A] = io.circe.parser.decode[A](input)
end ParserOps
