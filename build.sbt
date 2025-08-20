import com.peknight.build.gav.*
import com.peknight.build.sbt.*

commonSettings

lazy val ext = (project in file("."))
  .settings(name := "ext")
  .aggregate(
    catsExt.jvm,
    catsExt.js,
    catsExt.native,
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
    circeExt.native,
    circeParserExt.jvm,
    circeParserExt.js,
    circeParserExt.native,
    scodecBitsExt.jvm,
    scodecBitsExt.js,
    scodecBitsExt.native,
    http4sExt.jvm,
    http4sExt.js,
    log4CatsExt.jvm,
    log4CatsExt.js,
    log4CatsExt.native,
    scalaCheckExt.jvm,
    scalaCheckExt.js,
    scalaCheckExt.native,
    spireExt.jvm,
    spireExt.js,
  )

lazy val catsExt = (crossProject(JVMPlatform, JSPlatform, NativePlatform) in file("cats-ext"))
  .settings(name := "cats-ext")
  .settings(crossDependencies(typelevel.cats))

lazy val catsEffectExt = (crossProject(JVMPlatform, JSPlatform) in file("cats-effect-ext"))
  .settings(name := "cats-effect-ext")
  .settings(crossDependencies(typelevel.catsEffect))

lazy val catsParseExt = (crossProject(JVMPlatform, JSPlatform) in file("cats-parse-ext"))
  .settings(name := "cats-parse-ext")
  .settings(crossDependencies(typelevel.catsParse))

lazy val fs2Ext = (crossProject(JVMPlatform, JSPlatform) in file("fs2-ext"))
  .dependsOn(catsExt)
  .settings(name := "fs2-ext")
  .settings(crossDependencies(fs2))

lazy val fs2IOExt = (crossProject(JVMPlatform, JSPlatform) in file("fs2-io-ext"))
  .settings(name := "fs2-io-ext")
  .settings(crossDependencies(fs2.io))

lazy val circeExt = (crossProject(JVMPlatform, JSPlatform, NativePlatform) in file("circe-ext"))
  .settings(name := "circe-ext")
  .settings(crossDependencies(circe))

lazy val circeParserExt = (crossProject(JVMPlatform, JSPlatform, NativePlatform) in file("circe-parser-ext"))
  .settings(name := "circe-parser-ext")
  .settings(crossDependencies(
    circe.parser,
    circe.jawn
  ))

lazy val scodecBitsExt = (crossProject(JVMPlatform, JSPlatform, NativePlatform) in file("scodec-bits-ext"))
  .settings(name := "scodec-bits-ext")
  .settings(crossDependencies(scodec.bits))

lazy val http4sExt = (crossProject(JVMPlatform, JSPlatform) in file("http4s-ext"))
  .dependsOn(catsEffectExt)
  .settings(name := "http4s-ext")
  .settings(crossDependencies(http4s))

lazy val log4CatsExt = (crossProject(JVMPlatform, JSPlatform, NativePlatform) in file("log4cats-ext"))
  .settings(name := "log4cats-ext")
  .jvmSettings(libraryDependencies ++= dependencies(typelevel.log4Cats))

lazy val scalaCheckExt = (crossProject(JVMPlatform, JSPlatform, NativePlatform) in file("scalacheck-ext"))
  .settings(name := "scalacheck-ext")
  .settings(crossDependencies(scalaCheck))

lazy val spireExt = (crossProject(JVMPlatform, JSPlatform) in file("spire-ext"))
  .settings(name := "spire-ext")
  .settings(crossDependencies(typelevel.spire))
