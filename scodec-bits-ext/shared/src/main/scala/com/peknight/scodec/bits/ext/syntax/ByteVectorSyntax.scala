package com.peknight.scodec.bits.ext.syntax

import com.peknight.scodec.bits.ext.bigint.fromUnsignedBytes
import com.peknight.scodec.bits.ext
import scodec.bits.ByteVector

trait ByteVectorSyntax extends ByteVectorSyntaxPlatform:
  extension (bytes: ByteVector)
    def adjustLength(length: Int): ByteVector = ext.adjustLength(bytes, length)
    def toUnsignedBigInt: BigInt = fromUnsignedBytes(bytes)
    def leftHalf: ByteVector = ext.leftHalf(bytes)
    def rightHalf: ByteVector = ext.rightHalf(bytes)
  end extension
end ByteVectorSyntax
object ByteVectorSyntax extends ByteVectorSyntax
