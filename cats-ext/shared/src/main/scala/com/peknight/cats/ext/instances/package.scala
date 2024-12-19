package com.peknight.cats.ext

package object instances:
  object all extends ApplicativeInstances with EitherTInstances with EqInstances with OptionEitherTInstances
  object applicative extends ApplicativeInstances
  object eitherT extends EitherTInstances
  object eq extends EqInstances
  object optionEitherT extends OptionEitherTInstances
end instances
