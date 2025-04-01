package com.peknight.fs2.io.ext.syntax

import cats.effect.Sync
import fs2.io.file.Path

trait PathSyntax:
  extension (path: Path)
    def exists[F[_]: Sync]: F[Boolean] = Sync[F].blocking(path.toNioPath.toFile.exists())
  end extension
end PathSyntax
object PathSyntax extends PathSyntax
