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
    case above@Above(_, _) => Interval.atOrAbove(Year.of(BoundOps.lower(above.lowerBound).getYear))
    case below@Below(_, _) => Interval.atOrBelow(Year.of(BoundOps.upper(below.upperBound).getYear))
    case bounded@Bounded(_, _, _) =>
      Interval.closed(
        Year.of(BoundOps.lower(bounded.lowerBound).getYear),
        Year.of(BoundOps.upper(bounded.upperBound).getYear)
      )
    case Point(v) => Interval.point(Year.of(v.getYear))
    case Empty() => Interval.empty[Year]
  end mapToYear
end LocalDateIntervalOps
