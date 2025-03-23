package com.peknight.spire.ext.syntax

import com.peknight.spire.ext.math.interval.BoundOps
import spire.math.interval.ValueBound

import java.time.LocalDate
import scala.concurrent.duration.FiniteDuration

trait BoundSyntax:
  extension[N: Integral] (bound: ValueBound[N])
    def get(lower: Boolean): N = BoundOps.get(bound, lower)
    def lower: N = BoundOps.lower(bound)
    def upper: N = BoundOps.upper(bound)
  end extension

  extension (bound: ValueBound[LocalDate])
    def get(lower: Boolean): LocalDate = BoundOps.get(bound, lower)
    def lower: LocalDate = BoundOps.lower(bound)
    def upper: LocalDate = BoundOps.upper(bound)
  end extension
  
  extension (bound: ValueBound[FiniteDuration])
    def get(lower: Boolean): FiniteDuration = BoundOps.get(bound, lower)
    def lower: FiniteDuration = BoundOps.lower(bound)
    def upper: FiniteDuration = BoundOps.upper(bound)
  end extension
end BoundSyntax
object BoundSyntax extends BoundSyntax
