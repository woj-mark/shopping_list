package shopping_list.data

import cats.Monad
import cats.data.{EitherT, NonEmptyList}
import cats.syntax.functor._

import shopping_list.data.{ItemAlreadyExistsError, ItemNotFoundError}


/**
 * The Service provides the entry point to the Shopping_list domain
 * Repository and Validation algebras to be provided as implicits to
 * implement/determine the behaviour
 */
class Service[F[_]](repoAlgebra:RepositoryAlgebra,
                    validationAlgebra:ValidationAlgebra) {
  def create(item:Item)(implicit M : Monad[F]) : EitherT[F,ItemAlreadyExistsError, Item] = 
      repoAlgebra.create(item)

  def get(id:Int)(implicit M : Monad[F]) :EitherT[F,ItemNotFoundError.type, Item] = 
      EitherT.fromOptionF(repoAlgebra.get(id),ItemNotFoundError)


  def update(item:Item)(implicit M: Monad[F]) : EitherT[F,ItemNotFoundError.type, Item] = 
      for{
          _ <- validationAlgebra.exists(item.id)
          updated <- EitherT.fromOptionF(repoAlgebra.update(item),ItemNotFoundError)
      } yield updated

  def delete(id:Int)(implicit M: Monad[F]):F[Unit] = 
      repoAlgebra.delete(id).as()

  def findByName(name:String): F[Item] =
      repoAlgebra.findByName(name)

}

//A companion object with apply method for the Service
object Service{
    def apply[F[_]:Monad](repoAlgebra:RepositoryAlgebra,
                    validationAlgebra:ValidationAlgebra)=
    new Service[F](repoAlgebra,validationAlgebra)
                    
}
  

