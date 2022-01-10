package shopping_list
package data

/**
 * The Status sealed trait represents the different statuses that a product can be in.
 *
 */

sealed trait Status(val state:String)
case object Ordered extends Status("ordered")
case object Bought extends Status("bought")
case object Unavailable extends Status("unavailable")

object Status{
    private def states = Set(Ordered,Bought, NotAvailable)
    
    /**
     * A method used to (WM to compelte the description)
     */
    def unsafeFromString(state: String) : Status = {
        states.find(_.state == state).get
    }
}