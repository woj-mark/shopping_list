package shopping_list.data

import enumeratum._

/**
 * The Status sealed trait represents the different statuses that an Item can be in.
 *
 */

sealed trait Status extends EnumEntry

case object Status extends Enum[Status] with CirceEnum[Status]{
case object Ordered extends Status
case object Bought extends Status
case object Unavailable extends Status

val values = findValues
}
