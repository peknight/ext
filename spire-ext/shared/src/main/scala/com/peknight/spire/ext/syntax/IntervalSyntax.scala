package com.peknight.spire.ext.syntax

import com.peknight.spire.ext.math.interval.time.LocalDateIntervalOps
import spire.math.Interval

import java.time.{LocalDate, Year}

trait IntervalSyntax:
  extension (date: Interval[LocalDate])
    def mapToYear: Interval[Year] = LocalDateIntervalOps.mapToYear(date)
  end extension
end IntervalSyntax
object IntervalSyntax extends IntervalSyntax
