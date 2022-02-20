package shopping_list

import cats.effect._
import cats.syntax.functor._
import doobie.util.ExecutionContexts
import io.circe.config.parser
import org.http4s.server.blaze.BlazeServerBuilder
import org.http4s.server.{Server => H4Server}

import shopping_list.config.{DatabaseConfig,ShoppingListConfig}
import shopping_list.data.ItemNotFoundError


object Server extends IOApp {

  def createServer[F[_] :ContextShift: ConcurrentEffect: Timer] : Resource[F, H4Server[F]] =

  for {
    conf <- Resource.liftF(parser.decodePathF[F, ShoppingListConfig]("petstore"))
    fixedThreadPool  <- ExecutionContexts.fixedThreadPool[F](conf.database.connections.poolSize)
    cachedThreadPool <- ExecutionContexts.cachedThreadPool[F]
    _ <- DatabaseConfig.transactor(conf.database, fixedThreadPool, cachedThreadPool)
    transactor <- DatabaseConfig.transactor(conf.database, fixedThreadPool, cachedThreadPool)
_          = DoobieShoppingListInterpreter[F](transactor)
    shoplistRepository = DoobieItemRepositoryInterpreter[[F](transactor)
    shoplistValidation = ValidationInterpreter[F](petRepository)

    server <- BlazeServerBuilder[F]
        .bindHttp(conf.server.port, conf.server.host)
        .resource
    } yield server
  

  def run(args:List[String]): IO[ExitCode] = 
    createServer.use(_ => IO.never).as(ExitCode.Success)
}
