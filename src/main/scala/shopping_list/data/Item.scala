package shopping_list
package data

import io.circe._
import io.circe.generic.semiauto._
import cats.implicits._


/*
 * An Item represents an product/item to be bought.
 
 * @param id    The  ID of the item.
 * @param name The name of the item
 * @param status The status of the item on the list
 * 
 * 
 */

 final case class Item(
    id: Int,
    name: Name,
    status: Status
 )

 object Item {

//Using circe to automatically derive JSON decoders and encoders for the Item case class
     implicit val encode: Encoder[Item] = deriveEncoder[Item]

     implicit val decode: Decoder[Item] = deriveDecoder[Item]

 }