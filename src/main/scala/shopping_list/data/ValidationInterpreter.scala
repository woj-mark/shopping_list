package shopping_list.data

import cats.Monad
import cats.data.EitherT
import cats.syntax.applicative._
import cats.syntax.either._
import cats.syntax.functor._
import shopping_list.data.{ItemAlreadyExistsError, ItemNotFoundError}

class ValidationInterpreter[F[_]: Monad](repoAlgebra: RepositoryAlgebra[F]) 
extends ValidationAlgebra[F] {

  def exists(id:Item): EitherT[F,ItemNotFoundError.type,Unit] = {
      EitherT{
          id match{
              case some(itemId) => itemId match {
                  case Some(_) => Right(())
                  case _ => Left(ItemNotFoundError)
              
              case _ => Either.left[ItemNotFoundError.type, Unit](ItemNotFoundError).pure[F]
          }

          }
        }
    }

 def doesNotExist(item:Item):EitherT[F,ItemAlreadyExistsError, Unit] = {
     EitherT{
        repoAlgebra.findByName(item.name) match{
            case None => Left(ItemAlreadyExistsError)
            case Some(_) => Right(())
        }
     }

 }

 //Companion object implementing the apply method
 object ValidationInterpreter{
     def apply[F[_]: Monad](repoAlgebra:RepositoryAlgebra[F]) = 
         new ValidationInterpreter[F](repoAlgebra)
 }
}

    
  