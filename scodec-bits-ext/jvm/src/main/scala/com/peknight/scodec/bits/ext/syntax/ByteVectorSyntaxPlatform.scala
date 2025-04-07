package com.peknight.scodec.bits.ext.syntax

import scodec.bits.ByteVector

import java.io.ByteArrayInputStream

trait ByteVectorSyntaxPlatform:
  extension (byteVector: ByteVector)
    def toByteArrayInputStream: ByteArrayInputStream = ByteArrayInputStream(byteVector.toArray)
  end extension
end ByteVectorSyntaxPlatform
