package com.peknight.circe.parser.ext

import io.circe.{Decoder, Error, Json, ParsingFailure}

object ParserOps:
  def parse(input: String): Either[ParsingFailure, Json] = io.circe.parser.parse(input)
  def decode[A: Decoder](input: String): Either[Error, A] = io.circe.parser.decode[A](input)
end ParserOps
