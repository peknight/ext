package com.peknight.spire.ext.math.interval

import spire.math.interval.{Closed, Open, ValueBound}

object BoundOps:
  def get[N : Integral](bound: ValueBound[N], lower: Boolean): N =
    bound match
      case Open(a) => if lower then Integral[N].plus(a, Integral[N].one) else Integral[N].minus(a, Integral[N].one)
      case Closed(a) => a
