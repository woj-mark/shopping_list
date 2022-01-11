


import cats.effect.IO
import dbTypes.{ DatabaseLogin, DatabasePassword, DatabaseUrl }
import eu.timepit.refined.auto._
import org.flywaydb.core.Flyway

/**
  * An implementation of the database migrator using Flyway and IO.
  */


class FlywayDbMigator extends DbMigrator {
      /**
    * Apply pending migrations to the database.
    *
    * @param url  A JDBC database connection url.
    * @param user The login name for the connection.
    * @param pass The password for the connection.
    * 
    * @return The number of applied migrations.
    */
  
    override def migrate(url: DatabaseUrl, user: DatabaseLogin, pass: DatabasePassword) : IO[Int] =
        //The code is wrapped by an IO monad to constrain the side-effect
        IO{
            val flyway: Flyway = Flyway.configure().datasource(url, user, pass).load()
            flyway.migrate()
        }
}
