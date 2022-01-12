package shopping_list.data

import shopping_list.data.Item

/**
 * An interface defining the API to interact with the domain 
 * object Item
 * 
 * A tagless final approach with HKT F[-] used to abstract over the side-effect
 */
trait RepositoryAlgebra[F[_]] {

  def create(item: Item): F[Item]
  def update(item: Item): F[Option[Item]]
  def get(item: Int): F[Option[Item]]
  def delete(item: Int): F[Option[Item]]
}
