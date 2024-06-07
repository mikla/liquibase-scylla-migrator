ThisBuild / version := "0.1.0-SNAPSHOT"

ThisBuild / scalaVersion := "2.13.14"

ThisBuild / organization := "io.crypto.bro"

ThisBuild / libraryDependencies ++= Seq(
  "org.liquibase" % "liquibase-core" % "4.28.0",
  "com.ing.data" % "cassandra-jdbc-wrapper" % "4.12.0",
//  "com.github.geirolz" %% "fly4s" % "1.0.0",
//  "com.datastax.oss" % "java-driver-core" % "4.10.0",
//  "com.scylladb" % "java-driver-core" % "4.15.0.0"
)

lazy val root = (project in file("."))
  .settings(
    name := "liquibase-scylla-migrator"
  )
