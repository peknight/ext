package com.peknight.cats.ext

package object instances:
  object all extends ApplicativeInstances with EitherTInstances
  object applicative extends ApplicativeInstances
  object eitherT extends EitherTInstances
end instances
