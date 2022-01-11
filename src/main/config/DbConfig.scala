package config

import eu.timepit.refined.auto._
import eu.timepit.refined.pureconfig._
import pureconfig._
import pureconfig.generic.semiauto._

/**
  * The configuration for the database connection.
  *
  * @param driver The class name of the driver to use.
  * @param url    The JDBC connection url (driver specific).
  * @param user   The username for the database connection.
  * @param pass   The password for the database connection.
  */

final case class DbConfig(
    driver: NonEmptyString,
    url: DatabaseUrl,
    user: DatabaseLogin,
    pass: DatabasePassword
)


/**
 * A companion object with an implicit for the config to map
 * from a configuration to the data types. Function 'deriveReader'
 * derives the codes (similarly as for JSON). 
 * 
 * N.T.S. There is always additional overhead for automatic derivation 
 * by the compiler.
 */
object DatabaseConfig {

  implicit val configReader: ConfigReader[DatabaseConfig] = deriveReader[DatabaseConfig]

}