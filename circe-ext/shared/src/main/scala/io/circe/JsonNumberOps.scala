package io.circe

import io.circe.numbers.BiggerDecimal

object JsonNumberOps:
  def fromBiggerDecimal(value: BiggerDecimal, input: String): JsonNumber = JsonBiggerDecimal(value, input)
  def fromBigDecimal(value: BigDecimal): JsonNumber = JsonBigDecimal(value.underlying())
  def fromLong(value: Long): JsonNumber = JsonLong(value)
  def fromDouble(value: Double): JsonNumber = JsonDouble(value)
  def fromFloat(value: Float): JsonNumber = JsonFloat(value)
end JsonNumberOps
