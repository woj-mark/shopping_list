package shopping_list
package data

/*
 * A Name refined type representing the name of the Item.
 * The string has to be non-empty.
 *
 */
type Name = String Refined NonEmpty