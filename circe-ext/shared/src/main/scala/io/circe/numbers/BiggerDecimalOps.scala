package io.circe.numbers

object BiggerDecimalOps:
  def sigAndExp(unscaled: BigInt, scale: BigInt): BiggerDecimal = SigAndExp(unscaled.underlying(), scale.underlying())
end BiggerDecimalOps
