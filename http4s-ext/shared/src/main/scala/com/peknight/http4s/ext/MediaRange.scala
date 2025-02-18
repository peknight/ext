package com.peknight.http4s.ext

import org.http4s.MediaType

object MediaRange:
  val `application/json`: MediaType = MediaType.unsafeParse("application/json")
  val `application/problem+json`: MediaType = MediaType.unsafeParse("application/problem+json")
  val `application/problem+xml`: MediaType = MediaType.unsafeParse("application/problem+xml")
end MediaRange
