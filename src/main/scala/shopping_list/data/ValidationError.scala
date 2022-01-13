package shopping_list.data

import shopping_list.data.Item


/**
 * Definition of Validation Errors to be used in the shopping list application
 * This allow separation of logical errors from business errors (defined with own ADT)
 */

sealed trait ValidationError extends Item with Serializable

case class ItemAlreadyExistsError(item:Item) extends ValidationError

case object ItemNotFoundError extends ValidationError


