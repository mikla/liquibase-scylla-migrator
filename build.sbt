ThisBuild / version := "0.1.0-SNAPSHOT"

ThisBuild / scalaVersion := "2.13.14"

ThisBuild / organization := "io.crypto.bro"

ThisBuild / libraryDependencies ++= Seq(
  "org.liquibase.ext" % "liquibase-cassandra" % "4.28.0",
  "com.ing.data" % "cassandra-jdbc-wrapper" % "4.12.0",
  "com.scylladb" % "java-driver-core" % "4.15.0.0"
)

lazy val root = (project in file("."))
  .settings(
    name := "liquibase-scylla-migrator"
  )
