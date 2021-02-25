lazy val supportedScalaVersions = List("2.13.4", "2.12.12")

ThisBuild / organization := "org.tree_annotation"
ThisBuild / scalaVersion := "2.12.12"
ThisBuild / name := "tao-parser"
ThisBuild / description := "first truly universal syntax for structural communication at any scale, born of a vision of total intercommunication of all software systems"
ThisBuild / licenses := List("MIT" -> url("https://choosealicense.com/licenses/mit/"))
ThisBuild / homepage := Some(url("https://www.tree-annotation.org/"))
ThisBuild / scmInfo := Some(ScmInfo(url("https://github.com/tree-annotation/tao-parser-scala"), "https://github.com/tree-annotation/tao-parser-scala.git"))

ThisBuild / developers := List(Developer(
  "Truman A. Oz",
  "Truman A. Oz",
  """"truman" + "!@#".charAt(1) + "tree-annotation.org"""",
  url("https://github.com/trumanaoz")
))

ThisBuild / publishTo := sonatypePublishToBundle.value

lazy val root = (project in file("."))
  .enablePlugins(GitVersioning)
  .aggregate(taoParser)
  .settings(
    name := "tao-parser-root",
    crossScalaVersions := Nil
  )

lazy val commonDependenies = Seq(
  "org.slf4j" % "slf4j-api" % "1.7.30",
  "com.typesafe.scala-logging" %% "scala-logging" % "3.9.2" % Test,
  "ch.qos.logback" % "logback-classic" % "1.2.3" % Test,
  "org.scalatest" %% "scalatest" % "3.2.0" % Test
)

lazy val taoParser = (project in file("tao-parser"))
  .settings(
    name := "tao-parser",
    crossScalaVersions := supportedScalaVersions,
    libraryDependencies ++= commonDependenies
  )

