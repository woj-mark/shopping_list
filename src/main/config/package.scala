package shopping_list


import io.circe.Decoder
import io.circe.generic.semiauto._

package object config {
  implicit val databaseConnectionsConfigDecoder: Decoder[DatabaseConnectionsConfig] = deriveDecoder
  implicit val databaseConfigDecoder: Decoder[DatabaseConfig]                       = deriveDecoder
  implicit val serverConfigDecoder: Decoder[ServerConfig]                           = deriveDecoder
  implicit val ShoppingListConfigDecoder: Decoder[ShoppingListConfig]                       = deriveDecoder
}