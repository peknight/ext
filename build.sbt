ThisBuild / version := "0.1.0-SNAPSHOT"

ThisBuild / scalaVersion := "3.2.1"

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
    spireExt.jvm,
    spireExt.js
  )
  .enablePlugins(JavaAppPackaging)
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

lazy val spireExt = (crossProject(JSPlatform, JVMPlatform) in file("spire-ext"))
  .settings(commonSettings)
  .settings(
    name := "spire-ext",
    libraryDependencies ++= Seq(
      "org.typelevel" %%% "spire" % spireVersion,
    )
  )

val catsVersion = "2.9.0"
val spireVersion = "0.18.0"
