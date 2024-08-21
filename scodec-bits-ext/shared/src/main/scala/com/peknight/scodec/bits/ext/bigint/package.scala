package com.peknight.scodec.bits.ext

import scodec.bits.ByteVector

package object bigint:
  def fromUnsignedBytes(magnitude: ByteVector): BigInt = BigInt(1, magnitude.toArray)

  def toUnsignedBytes(bigInt: BigInt): ByteVector =
    val twosComplementBytes = ByteVector(bigInt.toByteArray)
    if bigInt.bitLength % 8 == 0 && twosComplementBytes.length > 1 && twosComplementBytes.head == 0 then
      twosComplementBytes.tail
    else
      twosComplementBytes

  def toUnsignedBytes(bigInt: BigInt, minLength: Int): ByteVector =
    val notPadded = toUnsignedBytes(bigInt)
    if notPadded.length >= minLength then notPadded
    else ByteVector.fill(minLength - notPadded.length)(0) ++ notPadded

  def toByteVector(bigInt: BigInt): ByteVector = ByteVector(bigInt.toByteArray)
end bigint
