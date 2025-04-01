package com.peknight.fs2.io.ext.syntax

import cats.effect.Sync
import cats.syntax.functor.*
import fs2.io.file.Path

import java.nio.file.Paths

trait PathSyntax:
  extension (path: Path)
    def exists[F[_]: Sync]: F[Boolean] = Sync[F].blocking(path.toNioPath.toFile.exists())
  end extension
  extension (path: Path.type)
    def fromResource[F[_]: Sync](resourcePath: String): F[Path] =
      Sync[F].blocking(Path.fromNioPath(Paths.get(getClass.getClassLoader.getResource(resourcePath).toURI)))
  end extension
end PathSyntax
object PathSyntax extends PathSyntax
