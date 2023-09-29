package com.peknight.spire.ext.syntax

import cats.kernel.Order
import com.peknight.spire.ext.math.interval.IntervalOps
import com.peknight.spire.ext.math.interval.time.LocalDateIntervalOps
import spire.math.Interval

import java.time.{LocalDate, Year}

trait IntervalSyntax:
  extension [N: Integral: Order] (interval: Interval[N])
    def close: Interval[N] = IntervalOps.close(interval)
  end extension

  extension (date: Interval[LocalDate])
    def mapToYear: Interval[Year] = LocalDateIntervalOps.mapToYear(date)
    def close: Interval[LocalDate] = IntervalOps.close(date)
  end extension
end IntervalSyntax
object IntervalSyntax extends IntervalSyntax
