package db


  /**
    * Interface defining how pending migrations are applied to the database.
    *
    * @param url  A JDBC database connection url.
    * @param user The login name for the connection.
    * @param pass The password for the connection.
    * 
    * @return The number of applied migrations.
    */

trait DbMigrator[F[_]] {
    def migrate(url: DatabaseUrl, user: DatabaseLogin, pass: DatabasePassword): F[Int]

}
