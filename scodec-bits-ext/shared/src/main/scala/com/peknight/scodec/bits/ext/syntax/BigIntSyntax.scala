package com.peknight.scodec.bits.ext.syntax

import com.peknight.scodec.bits.ext.bigint
import scodec.bits.ByteVector

trait BigIntSyntax:
  extension (bigInt: BigInt)
    def toUnsignedBytes: ByteVector = bigint.toUnsignedBytes(bigInt)
    def toUnsignedBytes(minLength: Int): ByteVector = bigint.toUnsignedBytes(bigInt, minLength)
  end extension
end BigIntSyntax
object BigIntSyntax extends BigIntSyntax
