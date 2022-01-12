package db


 /**
  * An interface defining the base of the DB migrator.
  *
  * @tparam F A higher kinded type which wraps the actual return value.
  */

trait DbMigrator[F[_]] {

   /**
    * Pending migrations are applied to the database.
    * 
    *
    * @param url  A JDBC database connection url.
    * @param user The login name for the connection.
    * @param pass The password for the connection.
    * 
    * @return The number of applied migrations.
    */
    
    def migrate(url: DatabaseUrl, user: DatabaseLogin, pass: DatabasePassword): F[Int]

}
