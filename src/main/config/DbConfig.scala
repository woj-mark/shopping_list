package shopping_list.config

import eu.timepit.refined.auto._
import eu.timepit.refined.pureconfig._
import pureconfig._
import pureconfig.generic.semiauto._


final case class DatabaseConnectionsConfig(poolSize: Int)


/**
  * The configuration for the database connection.
  *
  * @param driver The class name of the driver to use.
  * @param url    The JDBC connection url (driver specific).
  * @param user   The username for the database connection.
  * @param pass   The password for the database connection.
  */
  
final case class DatabaseConfig(
    driver: NonEmptyString,
    url: DatabaseUrl,
    user: DatabaseLogin,
    pass: DatabasePassword,
    connections: DatabaseConnectionsConfig)



/**
 * A companion object for configuraton of the database. ADD MORE comment
 * 
 */
object DatabaseConfig {
  def transactor[F[_]: Async: ContextShift](
      config: DatabaseConfig,
      fixedThreadPool: ExecutionContext,
      cachedThreadPool: ExecutionContext): Resource[F, HikariTransactor[F]] =
    HikariTransactor.newHikariTransactor[F](config.driver,
                                            config.url,
                                            config.user,
                                            config.password,
                                            fixedThreadPool,
                                            cachedThreadPool)

 def initializeDb[F[_]](config: DatabaseConfig)(implicit S: Sync[F]): F[Unit] =
    S.delay {
        val fw: Flyway = {
          Flyway.configure().dataSource(config.url, config.user, config.password).load()
        }
        fw.migrate()
      }
      .as(())

}

