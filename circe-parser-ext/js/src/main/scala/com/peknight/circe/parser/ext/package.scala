package com.peknight.circe.parser

import io.circe.{Decoder, Error, Json, ParsingFailure}

package object ext:
  def parse(input: String): Either[ParsingFailure, Json] = io.circe.jawn.parse(input)
  def decode[A: Decoder](input: String): Either[Error, A] = io.circe.jawn.decode[A](input)
end ext
