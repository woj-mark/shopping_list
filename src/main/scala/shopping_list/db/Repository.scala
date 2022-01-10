package db

import data.*
import fs2.Stream



/**
 * The Repository defines an interface for the business logic of the
 * shopping_list application.
 *
 * @tparam F A higher kinded type wrapping the actual return value.
 */

trait Repository[F[_]] {

     /**
    * Load an item from the database repository.
    *
    * @param id The id of the item
    * @return A database rows for the item
    */
  def loadItems(id:Int): F[(Int, Name, Status)]
  

   /**
    * Load all items from the database repository.
    *
    * @return A stream of database rows which you'll need to combine.
    */
  def loadItems(): Stream[F, (Int, Name, Status)]

  /**
    * Save the given item in the database.
    *
    * @param p A product to be saved.
    * @return The number of affected database rows
    */
  def saveItem(i: Item): F[Int]

  /**
    * Update the given item in the database.
    *
    * @param p The product to be updated.
    * @return The number of affected database rows.
    */
  def updateItem(p: Product): F[Int]

}
