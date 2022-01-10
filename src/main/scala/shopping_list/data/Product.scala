package shooping_list
package data

/*
 * A Product represents an item to be bought.
 *
 */

 final case class Product(
     id: Int,
     name: String,
     state: State
 )