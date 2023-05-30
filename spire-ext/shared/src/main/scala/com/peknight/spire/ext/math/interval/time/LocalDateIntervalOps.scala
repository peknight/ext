package com.peknight.spire.ext.math.interval.time

import cats.kernel.Order
import com.peknight.spire.ext.math.interval.BoundOps
import spire.math.*

import java.time.{LocalDate, Year}

object LocalDateIntervalOps:
  given Order[Year] with
    def compare(x: Year, y: Year): Int = x.compareTo(y)
  end given

  def mapToYear(date: Interval[LocalDate]): Interval[Year] = date match
    case All() => Interval.all[Year]
    case above@Above(_, _) => Interval.atOrAbove(Year.of(BoundOps.get(above.lowerBound, true).getYear))
    case below@Below(_, _) => Interval.atOrBelow(Year.of(BoundOps.get(below.upperBound, false).getYear))
    case bounded@Bounded(_, _, _) =>
      Interval.closed(
        Year.of(BoundOps.get(bounded.lowerBound, true).getYear),
        Year.of(BoundOps.get(bounded.upperBound, false).getYear)
      )
    case Point(v) => Interval.point(Year.of(v.getYear))
    case Empty() => Interval.empty[Year]
  end mapToYear
end LocalDateIntervalOps
