package com.peknight.cats.effect.ext

import cats.effect.Clock as CEClock

import java.time.Instant

object Clock:
  def realTimeInstant[F[_]: CEClock]: F[Instant] = CEClock[F].realTimeInstant
end Clock