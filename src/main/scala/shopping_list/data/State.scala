package shopping_list
package data

/**
 * The State sealed trait represents the different statuses that a product can be in.
 *
 */

sealed trait Status
case object ToBuy extends Status
case object Bought extends Status
case object NotAvailable extends Status