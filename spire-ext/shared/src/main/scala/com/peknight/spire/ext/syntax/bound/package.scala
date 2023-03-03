package com.peknight.spire.ext.syntax

import com.peknight.spire.ext.math.interval.BoundOps
import spire.math.interval.ValueBound

package object bound:
  extension [N : Integral] (bound: ValueBound[N])
    def get(lower: Boolean): N = BoundOps.get(bound, lower)
  end extension
