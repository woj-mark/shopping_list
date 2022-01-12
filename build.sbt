organization        := "io.github.woj-mark"
name                := "shopping_list"
version             := "0.0.1"
scalaVersion := "2.13.3"



scalacOptions ++= Seq("-Ypartial-unification")

resolvers += Resolver.sonatypeRepo("snapshots")


val http4sVersion          = "0.22.0"
val CatsVersion            = "2.6.1"
val CirceVersion           = "0.14.1"
val CirceConfigVersion     = "0.6.1"
val DoobieVersion          = "0.5.3"
val pureConfigVersion = "0.9.1"
val LogbackVersion     = "1.2.3"
val H2Version          = "1.4.199"
val FlywayVersion          = "5.2.4"




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
  "org.flywaydb"       % "flyway-core"          % FlywayVersion,


  "org.http4s"            %% "http4s-blaze-server"     % http4sVersion,
  "org.http4s"            %% "http4s-circe"            % http4sVersion,
  "org.http4s"            %% "http4s-dsl"              % http4sVersion,
  "org.http4s"            %% "http4s-blaze-client"     % http4sVersion,
  "com.h2database" %  "h2"                  % H2Version,
  "ch.qos.logback" %  "logback-classic"     % LogbackVersion
)

addCompilerPlugin("org.spire-math" %% "kind-projector" % "0.9.9")


