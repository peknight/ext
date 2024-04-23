package com.peknight.cats.ext.monad.transformer

import cats.Monad
import com.peknight.cats.ext.instances.optionEitherT.optionEitherTMonad

final case class OptionEitherT[F[_], E, A](value: F[Either[E, Option[A]]])
object OptionEitherT:
  given [F[_]: Monad, E]: Monad[[A] =>> OptionEitherT[F, E, A]] with
    def pure[A](x: A): OptionEitherT[F, E, A] = OptionEitherT(optionEitherTMonad[F, E].pure(x))
    def flatMap[A, B](fa: OptionEitherT[F, E, A])(f: A => OptionEitherT[F, E, B]): OptionEitherT[F, E, B] =
      OptionEitherT(optionEitherTMonad[F, E].flatMap[A, B](fa.value)(a => f(a).value))
    def tailRecM[A, B](a: A)(f: A => OptionEitherT[F, E, Either[A, B]]): OptionEitherT[F, E, B] =
      OptionEitherT(optionEitherTMonad[F, E].tailRecM[A, B](a)(a0 => f(a0).value))
  end given
end OptionEitherT
