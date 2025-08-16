import com.peknight.build.gav.*
import com.peknight.build.sbt.*

commonSettings

lazy val ext = (project in file("."))
  .aggregate(
    catsExt.jvm,
    catsExt.js,
    catsEffectExt.jvm,
    catsEffectExt.js,
    catsParseExt.jvm,
    catsParseExt.js,
    fs2Ext.jvm,
    fs2Ext.js,
    fs2IOExt.jvm,
    fs2IOExt.js,
    circeExt.jvm,
    circeExt.js,
    circeParserExt.jvm,
    circeParserExt.js,
    scodecBitsExt.jvm,
    scodecBitsExt.js,
    http4sExt.jvm,
    http4sExt.js,
    log4CatsExt.jvm,
    log4CatsExt.js,
    scalaCheckExt.jvm,
    scalaCheckExt.js,
    spireExt.jvm,
    spireExt.js,
  )
  .settings(
    name := "ext",
  )

lazy val catsExt = (crossProject(JVMPlatform, JSPlatform, NativePlatform) in file("cats-ext"))
  .settings(crossDependencies(typelevel.cats))
  .settings(
    name := "cats-ext",
  )

lazy val catsEffectExt = (crossProject(JVMPlatform, JSPlatform) in file("cats-effect-ext"))
  .settings(crossDependencies(typelevel.catsEffect))
  .settings(
    name := "cats-effect-ext",
  )

lazy val catsParseExt = (crossProject(JVMPlatform, JSPlatform) in file("cats-parse-ext"))
  .settings(crossDependencies(typelevel.catsParse))
  .settings(
    name := "cats-parse-ext",
  )

lazy val fs2Ext = (crossProject(JVMPlatform, JSPlatform) in file("fs2-ext"))
  .dependsOn(catsExt)
  .settings(crossDependencies(fs2))
  .settings(
    name := "fs2-ext",
  )

lazy val fs2IOExt = (crossProject(JVMPlatform, JSPlatform) in file("fs2-io-ext"))
  .settings(crossDependencies(fs2.io))
  .settings(
    name := "fs2-io-ext",
  )

lazy val circeExt = (crossProject(JVMPlatform, JSPlatform, NativePlatform) in file("circe-ext"))
  .settings(crossDependencies(circe))
  .settings(
    name := "circe-ext",
  )

lazy val circeParserExt = (crossProject(JVMPlatform, JSPlatform, NativePlatform) in file("circe-parser-ext"))
  .settings(crossDependencies(
    circe.parser,
    circe.jawn
  ))
  .settings(
    name := "circe-parser-ext",
  )

lazy val scodecBitsExt = (crossProject(JVMPlatform, JSPlatform, NativePlatform) in file("scodec-bits-ext"))
  .settings(crossDependencies(scodec.bits))
  .settings(
    name := "scodec-bits-ext",
  )

lazy val http4sExt = (crossProject(JVMPlatform, JSPlatform) in file("http4s-ext"))
  .dependsOn(catsEffectExt)
  .settings(crossDependencies(http4s))
  .settings(
    name := "http4s-ext",
  )

lazy val log4CatsExt = (crossProject(JVMPlatform, JSPlatform, NativePlatform) in file("log4cats-ext"))
  .jvmSettings(dependencies(typelevel.log4Cats))
  .settings(
    name := "log4cats-ext",
  )

lazy val scalaCheckExt = (crossProject(JVMPlatform, JSPlatform, NativePlatform) in file("scalacheck-ext"))
  .settings(crossDependencies(scalaCheck))
  .settings(
    name := "scalacheck-ext",
  )

lazy val spireExt = (crossProject(JVMPlatform, JSPlatform) in file("spire-ext"))
  .settings(crossDependencies(typelevel.spire))
  .settings(
    name := "spire-ext",
  )
