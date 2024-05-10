package org.scalacheck

import org.scalacheck.rng.Seed

object GenOps:
  def gen[T](f: (Gen.Parameters, Seed) => Gen.R[T]): Gen[T] = Gen.gen(f)

  def doApply[T](gen: Gen[T])(p: Gen.Parameters, seed: Seed): Gen.R[T] = gen.doApply(p, seed)
end GenOps