package com.peknight.spire.ext.math.interval

import spire.math.interval.{Closed, Open, ValueBound}

import java.time.LocalDate
import scala.concurrent.duration.*

object BoundOps:

  def get[N: Integral](bound: ValueBound[N], lower: Boolean): N =
    bound match
      case Open(a) => if lower then Integral[N].plus(a, Integral[N].one) else Integral[N].minus(a, Integral[N].one)
      case Closed(a) => a
  end get

  def lower[N: Integral](bound: ValueBound[N]): N =
    bound match
      case Open(a) => Integral[N].plus(a, Integral[N].one)
      case Closed(a) => a
  end lower

  def upper[N: Integral](bound: ValueBound[N]): N =
    bound match
      case Open(a) => Integral[N].minus(a, Integral[N].one)
      case Closed(a) => a
  end upper

  def get(bound: ValueBound[LocalDate], lower: Boolean): LocalDate =
    bound match
      case Open(a) => if lower then a.plusDays(1) else a.minusDays(1)
      case Closed(a) => a
  end get

  def lower(bound: ValueBound[LocalDate]): LocalDate =
    bound match
      case Open(a) => a.plusDays(1)
      case Closed(a) => a
  end lower

  def upper(bound: ValueBound[LocalDate]): LocalDate =
    bound match
      case Open(a) => a.minusDays(1)
      case Closed(a) => a
  end upper

  def get(bound: ValueBound[FiniteDuration], lower: Boolean): FiniteDuration =
    bound match
      case Open(a) => if lower then a + 1.nano else a - 1.nano
      case Closed(a) => a
  end get

  def lower(bound: ValueBound[FiniteDuration]): FiniteDuration =
    bound match
      case Open(a) => a + 1.nano
      case Closed(a) => a
  end lower

  def upper(bound: ValueBound[FiniteDuration]): FiniteDuration =
    bound match
      case Open(a) => a - 1.nano
      case Closed(a) => a
  end upper
end BoundOps
