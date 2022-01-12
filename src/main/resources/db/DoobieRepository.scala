package shopping_list.db


import cats.effect.Sync
import data.*
import doobie._
import doobie.implicits._
import doobie.postgres.implicits._
import doobie.refined.implicits._
import eu.timepit.refined.auto._
import fs2.Stream

import scala.collection.immutable.Seq


/**
 * Singleton object defining the SQL queries used in the Shopping_list app
 */
private object ItemSQL {
    implicit val itemTypeMeta : Meta
}





/**
  * Implementation of a DB repository using doobie
  *
  * @param tx A transactor for actually executing our queries.
  */
final class DoobieRepository[F[_]: Sync](tx: Transactor[F]) extends Repository[F]  {
    /**
    * Load an item from the database repository.
    *
    * @param id The id of the item
    * @return A database rows for the item
    */
  def loadItem(id:Int): F[Item] = 
        sql"""SELECT id, name, status 
          FROM items
          WHERE id = $id"""
          .query[Item]
          .transact(tx)

   /**
    * Load all items from the database repository.
    *
    * @return A stream of database rows which you'll need to combine.
    */
  def loadItems(): Stream[F, (Int, Name, Status)]
        sql"""SELECT * FROM items"""
          .query[Item]
          .stream
          .transact(tx)


  /**
    * Save the given item in the database.
    *
    * @param p A product to be saved.
    * @return The number of affected database rows
    */
  def saveItem(i: Item): F[Int] =
      sql"INSERT INTO items (id,name,status) VALUES (${item.id}, ${item.name},${item.status})"



  /**
    * Update the given item in the database.
    *
    * @param p The product to be updated.
    * @return The number of affected database rows.
    */
  def updateItem(p: Product): F[Int]

  
}
