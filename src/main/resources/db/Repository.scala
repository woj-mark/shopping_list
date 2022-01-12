package shopping_list.db

import cats.effect.IO
import shopping_list.Errors._



/**
 * The Repository defines an interface abstracting the fundametal ways  
 * of communication with the database
 */

trait Repository[T] {

  def create(data:T) : IO[Either[Errors, T]]

  def get(id:Int):  IO[Either[Errors, T]]
  
  def update(id:Int):  IO[Either[Errors, T]]

  def delete(id: Int): IO[Either[Errors, Unit]]
}
