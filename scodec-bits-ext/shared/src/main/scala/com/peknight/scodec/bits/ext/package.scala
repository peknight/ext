package com.peknight.scodec.bits

import scodec.bits.ByteVector

package object ext:
  def adjustLength(bytes: ByteVector, length: Int): ByteVector =
    if bytes.length > length then bytes.take(length)
    else if bytes.length == length then bytes
    else bytes ++ ByteVector.fill(length - bytes.length)(0)
  def leftHalf(bytes: ByteVector): ByteVector = bytes.take(bytes.length / 2)
  def rightHalf(bytes: ByteVector): ByteVector = bytes.drop(bytes.length / 2)
end ext
