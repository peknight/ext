package com.peknight.fs2.io.ext.syntax

import cats.effect.Sync
import cats.syntax.functor.*
import fs2.io.file.Path

import java.io.{FileInputStream, FileOutputStream, FileReader, FileWriter}
import java.nio.file.Paths

trait PathSyntax:
  extension (path: Path)
    def toFileReader[F[_]: Sync]: F[FileReader] = Sync[F].blocking(new FileReader(path.toNioPath.toFile))
    def toFileWriter[F[_]: Sync]: F[FileWriter] = Sync[F].blocking(new FileWriter(path.toNioPath.toFile))
    def toFileInputStream[F[_]: Sync]: F[FileInputStream] =
      Sync[F].blocking(new FileInputStream(path.toNioPath.toFile))
    def toFileOutputStream[F[_]: Sync]: F[FileOutputStream] =
      Sync[F].blocking(new FileOutputStream(path.toNioPath.toFile))
  end extension
  extension (path: Path.type)
    def fromResource[F[_]: Sync](resourcePath: String): F[Path] =
      Sync[F].blocking(Path.fromNioPath(Paths.get(getClass.getClassLoader.getResource(resourcePath).toURI)))
  end extension
end PathSyntax
object PathSyntax extends PathSyntax
