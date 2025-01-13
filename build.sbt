ThisBuild / version := "0.1.0-SNAPSHOT"

ThisBuild / scalaVersion := "3.6.2"

ThisBuild / organization := "com.peknight"

lazy val commonSettings = Seq(
  scalacOptions ++= Seq(
    "-feature",
    "-deprecation",
    "-unchecked",
    "-Xfatal-warnings",
    "-language:strictEquality",
    "-Xmax-inlines:64"
  ),
)

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
    circeExt.jvm,
    circeExt.js,
    circeParserExt.jvm,
    circeParserExt.js,
    scodecBitsExt.jvm,
    scodecBitsExt.js,
    http4sExt.jvm,
    http4sExt.js,
    scalaCheckExt.jvm,
    scalaCheckExt.js,
    spireExt.jvm,
    spireExt.js,
  )
  .settings(commonSettings)
  .settings(
    name := "ext",
  )

lazy val catsExt = (crossProject(JSPlatform, JVMPlatform) in file("cats-ext"))
  .settings(commonSettings)
  .settings(
    name := "cats-ext",
    libraryDependencies ++= Seq(
      "org.typelevel" %%% "cats-core" % catsVersion,
    )
  )

lazy val catsEffectExt = (crossProject(JSPlatform, JVMPlatform) in file("cats-effect-ext"))
  .settings(commonSettings)
  .settings(
    name := "cats-effect-ext",
    libraryDependencies ++= Seq(
      "org.typelevel" %%% "cats-effect" % catsEffectVersion,
    )
  )

lazy val catsParseExt = (crossProject(JSPlatform, JVMPlatform) in file("cats-parse-ext"))
  .settings(commonSettings)
  .settings(
    name := "cats-parse-ext",
    libraryDependencies ++= Seq(
      "org.typelevel" %%% "cats-parse" % catsParseVersion,
    )
  )
lazy val fs2Ext = (crossProject(JSPlatform, JVMPlatform) in file("fs2-ext"))
  .settings(commonSettings)
  .settings(
    name := "fs2-ext",
    libraryDependencies ++= Seq(
      "co.fs2" %%% "fs2-core" % fs2Version,
    )
  )

lazy val circeExt = (crossProject(JSPlatform, JVMPlatform) in file("circe-ext"))
  .settings(commonSettings)
  .settings(
    name := "circe-ext",
    libraryDependencies ++= Seq(
      "io.circe" %%% "circe-core" % circeVersion,
    )
  )

lazy val circeParserExt = (crossProject(JSPlatform, JVMPlatform) in file("circe-parser-ext"))
  .settings(commonSettings)
  .settings(
    name := "circe-parser-ext",
    libraryDependencies ++= Seq(
      "io.circe" %%% "circe-parser" % circeVersion,
      "io.circe" %%% "circe-jawn" % circeVersion,
    )
  )

lazy val scodecBitsExt = (crossProject(JSPlatform, JVMPlatform) in file("scodec-bits-ext"))
  .settings(commonSettings)
  .settings(
    name := "scodec-bits-ext",
    libraryDependencies ++= Seq(
      "org.scodec" %%% "scodec-bits" % scodecVersion,
    )
  )

lazy val http4sExt = (crossProject(JSPlatform, JVMPlatform) in file("http4s-ext"))
  .settings(commonSettings)
  .settings(
    name := "http4s-ext",
    libraryDependencies ++= Seq(
      "org.http4s" %%% "http4s-core" % http4sVersion,
    )
  )

lazy val scalaCheckExt = (crossProject(JSPlatform, JVMPlatform) in file("scalacheck-ext"))
  .settings(commonSettings)
  .settings(
    name := "scalacheck-ext",
    libraryDependencies ++= Seq(
      "org.scalacheck" %%% "scalacheck" % scalaCheckVersion,
    )
  )

lazy val spireExt = (crossProject(JSPlatform, JVMPlatform) in file("spire-ext"))
  .settings(commonSettings)
  .settings(
    name := "spire-ext",
    libraryDependencies ++= Seq(
      "org.typelevel" %%% "spire" % spireVersion,
    )
  )

val catsVersion = "2.12.0"
val catsEffectVersion = "3.5.7"
val catsParseVersion = "0.3.10"
val fs2Version = "3.11.0"
val circeVersion = "0.14.10"
val scodecVersion = "1.2.1"
val http4sVersion = "1.0.0-M34"
val spireVersion = "0.18.0"
val scalaCheckVersion = "1.18.1"
