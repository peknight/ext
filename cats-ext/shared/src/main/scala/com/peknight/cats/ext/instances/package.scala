package com.peknight.cats.ext

package object instances:
  object all extends ApplicativeInstances with EitherTInstances with OptionEitherTInstances
  object applicative extends ApplicativeInstances
  object eitherT extends EitherTInstances
  object optionEitherT extends OptionEitherTInstances
end instances
