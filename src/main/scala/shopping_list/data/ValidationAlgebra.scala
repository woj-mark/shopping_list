package shopping_list.data

import shopping_list.data.{ItemAlreadyExistsError, ItemNotFoundError}

import cats.data.EitherT

/**
 * An interface defining the validation algrebra and error handling
 * 
 * EitherT [F[_], A, B] is used as a lighteight wrapper for 
 *  F[Either[A, B]] that makes it easy to compose Either and 
 *  F together
 */
trait ValidationAlgebra {

  def doesNotExist(item:Item):EitherT[F,ItemAlreadyExistsError, Unit]
  
  def exists(item:Item): EitherT[F,ItemNotFoundError,Unit]
}
