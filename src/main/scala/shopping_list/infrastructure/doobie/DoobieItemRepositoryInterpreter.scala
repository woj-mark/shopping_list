package shopping_list.infrastructure.doobie


import cats.Monad
import cats.data.{NonEmptyList, OptionT}
import cats.syntax.functor._
import cats.syntax.option._
import doobie.free.connection.ConnectionIO
import doobie.implicits._
import doobie.util.fragment.Fragment
import doobie.util.query.Query0
import doobie.util.transactor.Transactor
import doobie.util.update.Update0
import doobie.util.{Meta, fragments => Fragments}

import shopping_list.data.Item


/**
 * Singleton object defining the SQL queries used in the Shopping_list app
 * It connects the API algebra with a concrete interpreter
 */
private object ItemSQL{

//`StatusMeta` is requited to handle the ADT 'Item'
  implicit val StatusMeta: Meta[Status] = Meta[String].imap(Status.withName)(_.entryName)


  def select(id: Int): Query0[Item] =
    sql"""SELECT id, name, status
          FROM items
          WHERE id = $id
      """.query

  def insert(item: Item): Update0 =
    sql"""INSERT INTO items (id, name, status)
          VALUES (${item.id}, ${item.name}, ${item.status})
      """.update


  def update(item: Item, id: Int): Update0 =
    sql"""UPDATE items
          SET id = ${item.id}}, name = ${item.name}, status = ${id.status}
          WHERE id = $id
      """.update

  def delete(id: Int): Update0 =
    sql"""DELETE FROM items WHERE id = $id""".update

}


class DoobieItemRepositoryInterpreter[F[_]: Monad](val transactor: Transactor[F]) extends RepositoryAlgebra[F] 
{
  import ItemSQL._

def create(item: Item): F[Item] =
    insert(item)
      .withUniqueGeneratedKeys[Int]("id")
      .map(id => item.copy(id = id.some))
      .transact(transactor)

  def update(item: Item): F[Option[Item]] =
    OptionT.fromOption[ConnectionIO](item.id)
      .semiflatMap(id => ItemSQL.update(pet, id).run.as(item))
      .value
      .transact(transactor)

  def get(id: Int): F[Option[Item]] = 
      select(id)
      .option
      .transact(transactor)

  def delete(id: Long): F[Option[Item]] =
    OptionT(get(id)).semiflatMap(pet => ItemSQL.delete(id).run.transact(transactor).as(item)).value






}