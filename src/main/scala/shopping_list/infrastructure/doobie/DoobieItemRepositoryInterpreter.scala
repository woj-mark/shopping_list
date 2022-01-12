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
  implicit val StatusMeta: Meta[PetStatus] =
Meta[String].imap(PetStatus.withName)(_.entryName)


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



}
