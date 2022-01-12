package shopping_list

import doobie.enum.SqlState
import org.http4s.Status

/**
 * Definition of Errors messages to be used in the shopping list application
 */
object Errors {

    abstract class Errors(val message:String){}

    object NotFoundError extends Errors("Not Found")

    object UniqueConstraintError extends Errors("Duplicate data")

    case class CustomError(state: SqlState) extends Errors("SQL error: " + state.value)

}
