package shopping_list
package data

/*
 * A Product represents an item to be bought.
 *
 */

 final case class Product(
     id: Option[Int],
     name: String,
     status: Status
 )