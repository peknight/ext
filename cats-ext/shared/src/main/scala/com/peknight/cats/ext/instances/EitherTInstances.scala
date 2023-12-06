package com.peknight.cats.ext.instances

import cats.Monad
import cats.data.EitherT
import cats.syntax.applicative.*
import cats.syntax.either.*

trait EitherTInstances:
  given [F[_]: Monad, E]: Monad[[T] =>> F[Either[E, T]]] with
    def flatMap[A, B](fa: F[Either[E, A]])(f: A => F[Either[E, B]]): F[Either[E, B]] =
      EitherT(fa).flatMap(a => EitherT(f(a))).value
    def tailRecM[A, B](a: A)(f: A => F[Either[E, Either[A, B]]]): F[Either[E, B]] =
      Monad[[X] =>> EitherT[F, E, X]].tailRecM[A, B](a)(a => EitherT(f(a))).value
    def pure[A](x: A): F[Either[E, A]] = x.asRight[E].pure[F]
  end given
end EitherTInstances
object EitherTInstances extends EitherTInstances
