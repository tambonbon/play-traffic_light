name := """hello-world-play"""
organization := "com.example"

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayScala)

scalaVersion := "2.13.3"

libraryDependencies += guice
libraryDependencies += "org.scalatestplus.play" %% "scalatestplus-play" % "5.0.0" % Test
libraryDependencies += ws
libraryDependencies += ehcache
libraryDependencies += "com.softwaremill.macwire" %% "macros" % "2.3.3" % "provided"
libraryDependencies += "com.h2database" % "h2" % "1.4.197" % Test
libraryDependencies += "org.scalatest" %% "scalatest" % "3.1.0" % Test
libraryDependencies += "net.codingwell" %% "scala-guice" % "4.2.11"
libraryDependencies += jdbc
libraryDependencies += "com.typesafe.slick" %% "slick" % "3.3.3"
// Adds additional packages into Twirl
//TwirlKeys.templateImports += "com.example.controllers._"

// Adds additional packages into conf/routes
// play.sbt.routes.RoutesKeys.routesImport += "com.example.binders._"
