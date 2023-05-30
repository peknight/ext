package com.peknight.spire.ext.syntax

import com.peknight.spire.ext.math.interval.BoundOps
import spire.math.interval.ValueBound

import java.time.LocalDate

trait BoundSyntax:
  extension[N: Integral] (bound: ValueBound[N])
    def get(lower: Boolean): N = BoundOps.get(bound, lower)
  end extension

  extension (bound: ValueBound[LocalDate])
    def get(lower: Boolean): LocalDate = BoundOps.get(bound, lower)
  end extension
end BoundSyntax
object BoundSyntax extends BoundSyntax
