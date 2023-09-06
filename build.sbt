ThisBuild / version := "0.1.0-SNAPSHOT"

ThisBuild / scalaVersion := "3.3.0"

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
    fs2Ext.jvm,
    fs2Ext.js,
    spireExt.jvm,
    spireExt.js
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

lazy val fs2Ext = (crossProject(JSPlatform, JVMPlatform) in file("fs2-ext"))
  .settings(commonSettings)
  .settings(
    name := "fs2-ext",
    libraryDependencies ++= Seq(
      "co.fs2" %%% "fs2-core" % fs2Version,
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

val catsVersion = "2.10.0"
val fs2Version = "3.9.1"
val spireVersion = "0.18.0"
