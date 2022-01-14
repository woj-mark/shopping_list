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
      for{
     _ <- validationAlgebra.liftF(repoAlgebra.doesNotExist(item))
     created <-EitherT.liftF(repoAlgebra.create(item))
      } created

  def get(id:Int)(implicit M : Monad[F]) :EitherT[F,ItemNotFoundError.type, Item] = 
      EitherT.fromOptionF(repoAlgebra.get(id),ItemNotFoundError)


  def update(item:Item)(implicit M: Monad[F]) : EitherT[F,ItemNotFoundError.type, Item] = 
      for{
          _ <- validationAlgebra.exists(item.id)
          updated <- EitherT.fromOptionF(repoAlgebra.update(item),ItemNotFoundError)
      } updated

  def delete(id:Int)(implicit M: Monad[F]):F[Umit] = 
      repoAlgebra.delete(id).as()


                    }

  

