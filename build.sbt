import com.peknight.build.gav.typelevel
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

lazy val catsExt = (crossProject(JSPlatform, JVMPlatform) in file("cats-ext"))
  .settings(crossDependencies(typelevel.cats))
  .settings(
    name := "cats-ext",
  )

lazy val catsEffectExt = (crossProject(JSPlatform, JVMPlatform) in file("cats-effect-ext"))
  .settings(
    name := "cats-effect-ext",
    libraryDependencies ++= Seq(
      "org.typelevel" %%% "cats-effect" % catsEffectVersion,
    )
  )

lazy val catsParseExt = (crossProject(JSPlatform, JVMPlatform) in file("cats-parse-ext"))
  .settings(
    name := "cats-parse-ext",
    libraryDependencies ++= Seq(
      "org.typelevel" %%% "cats-parse" % catsParseVersion,
    )
  )
lazy val fs2Ext = (crossProject(JSPlatform, JVMPlatform) in file("fs2-ext"))
  .dependsOn(catsExt)
  .settings(
    name := "fs2-ext",
    libraryDependencies ++= Seq(
      "co.fs2" %%% "fs2-core" % fs2Version,
    )
  )

lazy val fs2IOExt = (crossProject(JSPlatform, JVMPlatform) in file("fs2-io-ext"))
  .settings(
    name := "fs2-io-ext",
    libraryDependencies ++= Seq(
      "co.fs2" %%% "fs2-io" % fs2Version,
    )
  )

lazy val circeExt = (crossProject(JSPlatform, JVMPlatform) in file("circe-ext"))
  .settings(
    name := "circe-ext",
    libraryDependencies ++= Seq(
      "io.circe" %%% "circe-core" % circeVersion,
    )
  )

lazy val circeParserExt = (crossProject(JSPlatform, JVMPlatform) in file("circe-parser-ext"))
  .settings(
    name := "circe-parser-ext",
    libraryDependencies ++= Seq(
      "io.circe" %%% "circe-parser" % circeVersion,
      "io.circe" %%% "circe-jawn" % circeVersion,
    )
  )

lazy val scodecBitsExt = (crossProject(JSPlatform, JVMPlatform) in file("scodec-bits-ext"))
  .settings(
    name := "scodec-bits-ext",
    libraryDependencies ++= Seq(
      "org.scodec" %%% "scodec-bits" % scodecVersion,
    )
  )

lazy val http4sExt = (crossProject(JSPlatform, JVMPlatform) in file("http4s-ext"))
  .dependsOn(catsEffectExt)
  .settings(
    name := "http4s-ext",
    libraryDependencies ++= Seq(
      "org.http4s" %%% "http4s-core" % http4sVersion,
    )
  )

lazy val log4CatsExt = (crossProject(JSPlatform, JVMPlatform) in file("log4cats-ext"))
  .settings(
    name := "log4cats-ext",
    libraryDependencies ++= Seq(
    )
  )
  .jvmSettings(
    libraryDependencies ++= Seq(
      log4CatsCore,
    )
  )

lazy val scalaCheckExt = (crossProject(JSPlatform, JVMPlatform) in file("scalacheck-ext"))
  .settings(
    name := "scalacheck-ext",
    libraryDependencies ++= Seq(
      "org.scalacheck" %%% "scalacheck" % scalaCheckVersion,
    )
  )

lazy val spireExt = (crossProject(JSPlatform, JVMPlatform) in file("spire-ext"))
  .settings(
    name := "spire-ext",
    libraryDependencies ++= Seq(
      "org.typelevel" %%% "spire" % spireVersion,
    )
  )

val catsVersion = "2.13.0"
val catsEffectVersion = "3.6.1"
val catsParseVersion = "0.3.10"
val fs2Version = "3.12.0"
val circeVersion = "0.14.13"
val scodecVersion = "1.2.1"
val http4sVersion = "1.0.0-M34"
val log4CatsVersion = "2.7.1"
val spireVersion = "0.18.0"
val scalaCheckVersion = "1.18.1"

val log4CatsCore = "org.typelevel" %% "log4cats-core" % log4CatsVersion
