package crypto

import liquibase.command.CommandScope
import liquibase.command.core.UpdateCommandStep
import liquibase.command.core.helpers.DbUrlConnectionArgumentsCommandStep

trait LiquibaseMigration {

  def run(dbConfig: DBConfig): Unit

}

case class DBConfig(
    url: String,
    driver: String,
    username: String,
    password: String
)

object LiquibaseMigration {

  val masterChangeLogFile = "migrations/changelog.sql"

  def apply(
      changelogFilePath: String = masterChangeLogFile
  ): LiquibaseMigration = new LiquibaseMigration {

    override def run(
        dbConfig: DBConfig
    ): Unit = {

      val base = new CommandScope("update")
        .addArgumentValue(
          DbUrlConnectionArgumentsCommandStep.URL_ARG,
          dbConfig.url
        )
        .addArgumentValue(
          DbUrlConnectionArgumentsCommandStep.DRIVER_ARG,
          dbConfig.driver
        )
        .addArgumentValue(
          DbUrlConnectionArgumentsCommandStep.USERNAME_ARG,
          dbConfig.username
        )
        .addArgumentValue(
          DbUrlConnectionArgumentsCommandStep.PASSWORD_ARG,
          dbConfig.password
        )
        .addArgumentValue(
          UpdateCommandStep.CHANGELOG_FILE_ARG,
          changelogFilePath
        )

      base.validate()

      base.execute()

    }
  }

}

object LiquibaseMigrationApp extends App {

  System.setProperty("liquibase.logging", "debug")

  val dbConfig = DBConfig(
    url = "jdbc:cassandra://localhost:9042/crypto?localdatacenter=datacenter1",
    driver = "com.ing.data.cassandra.jdbc.CassandraDriver",
    username = "admin",
    password = "admin"
  )

  val migrator = LiquibaseMigration()

  LiquibaseMigration().run(dbConfig)

}
