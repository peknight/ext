package com.peknight.cats.effect.ext

import cats.effect.Sync

import java.time.Instant

object Clock:
  def realTimeInstant[F[_]: Sync]: F[Instant] = Sync[F].blocking(Instant.now())
end Clock