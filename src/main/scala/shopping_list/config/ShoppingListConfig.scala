package shopping_list.config

final case class ServerConfig(host: String, port: Int)
final case class ShoppingListConfig(database: DatabaseConfig, server: ServerConfig)
