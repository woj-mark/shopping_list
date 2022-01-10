organization        := "io.github.woj-mark"
name                := "shopping_list"
version             := "0.0.1"
scalaVersion := "2.13.3"


val http4sVersion          = "0.18.4"
val CatsVersion            = "1.6.0"
val CirceVersion           = "0.9.3"
val CirceConfigVersion     = "0.6.1"
val DoobieVersion          = "0.5.3"
val pureConfigVersion = "0.9.1"

libraryDependencies += ++= Seq(
 "org.scala-lang.modules" %% "scala-parser-combinators" % "1.1.2",
   "org.typelevel"         %% "cats-core"              % CatsVersion,

  "io.circe"              %% "circe-generic"          % CirceVersion,
  "io.circe"              %% "circe-literal"          % CirceVersion,
  "io.circe"              %% "circe-generic-extras"   % CirceVersion,
  "io.circe"              %% "circe-parser"           % CirceVersion,
  "io.circe"              %% "circe-config"           % CirceConfigVersion,

   "org.tpolecat"          %% "doobie-core"            % DoobieVersion,
  "org.tpolecat"          %% "doobie-h2"              % DoobieVersion,
  "org.tpolecat"          %% "doobie-scalatest"       % DoobieVersion,
  "org.tpolecat"          %% "doobie-hikari"          % DoobieVersion,

  "org.http4s"            %% "http4s-blaze-server"     % http4sVersion,
  "org.http4s"            %% "http4s-circe"            % http4sVersion,
  "org.http4s"            %% "http4s-dsl"              % http4sVersion,
  "org.http4s"            %% "http4s-blaze-client"            % http4sVersion,
)


