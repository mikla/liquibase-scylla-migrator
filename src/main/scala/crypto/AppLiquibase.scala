package crypto

import java.net.InetSocketAddress
import java.sql.DriverManager

import com.datastax.oss.driver.api.core.CqlSession
import liquibase.Liquibase
import liquibase.changelog.DatabaseChangeLog
import liquibase.database.DatabaseFactory
import liquibase.database.jvm.JdbcConnection
import liquibase.resource.FileSystemResourceAccessor

import scala.jdk.CollectionConverters.CollectionHasAsScala
import scala.jdk.CollectionConverters.SeqHasAsJava

object AppLiquibase extends App {

  val connectionPoints: List[InetSocketAddress] = List(new InetSocketAddress("localhost", 9042))

  def scyllaSession = {
    CqlSession
      .builder()
      .addContactPoints(connectionPoints.asJava)
      .withLocalDatacenter("us")
      .withAuthCredentials("admin", "admin")
      .withKeyspace("crypto")
      .build()
  }

  def migrate(): Unit = {
    val changelogFile = "sb.xml"
    val url = s"jdbc:cassandra://localhost:9042/crypto?localdatacenter=datacenter1"

    val databaseFactory = DatabaseFactory.getInstance()
    println(databaseFactory.getImplementedDatabases.asScala.toList)

    val connection = DriverManager.getConnection(url, "admin", "admin")
    val database   = DatabaseFactory.getInstance().findCorrectDatabaseImplementation(new JdbcConnection(connection))

    val databChangeLog = new DatabaseChangeLog(
      "/Users/user/projects/personal/liquibase-scylla-migrator/src/main/resources/migrations/flyway/sb.xml"
    )

    val liquibase  = new Liquibase(databChangeLog, new FileSystemResourceAccessor(), database)

    liquibase.update("")
  }

 migrate()

}
