package com.peknight.spire.ext.math.interval

import cats.kernel.Order
import spire.math.Interval
import spire.math.interval.{Closed, Open}

import java.time.LocalDate

object IntervalOps:

  given Order[LocalDate] with
    def compare(x: LocalDate, y: LocalDate): Int = x.compareTo(y)
  end given

  def close[N: Integral: Order](interval: Interval[N]): Interval[N] =
    val lowerBound = interval.lowerBound match
      case Open(lower) => Closed(Integral[N].plus(lower, Integral[N].one))
      case lower => lower
    val upperBound = interval.upperBound match
      case Open(upper) => Closed(Integral[N].minus(upper, Integral[N].one))
      case upper => upper
    Interval.fromBounds(lowerBound, upperBound)

  def close(interval: Interval[LocalDate]): Interval[LocalDate] =
    val lowerBound = interval.lowerBound match
      case Open(lower) => Closed(lower.plusDays(1))
      case lower => lower
    val upperBound = interval.upperBound match
      case Open(upper) => Closed(upper.minusDays(1))
      case upper => upper
    Interval.fromBounds(lowerBound, upperBound)
end IntervalOps
