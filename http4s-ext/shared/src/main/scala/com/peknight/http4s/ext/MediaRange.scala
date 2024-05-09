package com.peknight.http4s.ext

import org.http4s.MediaType

object MediaRange:
  val `application/json`: MediaType = MediaType.unsafeParse("application/json")
  val `application/jose+json`: MediaType = MediaType.unsafeParse("application/jose+json")
end MediaRange
