name := "Conceptual"
version := "1.0"
scalaVersion := "2.11.8"

resolvers += "git-ryanmarcus" at "https://github.com/RyanMarcus/maven-repo/raw/master/"

libraryDependencies ++= Seq(
  "edu.brandeis" % "ggen4j" % "0.0.1",
  "com.beachape" %% "enumeratum" % "1.4.15",
  "org.scalactic" %% "scalactic" % "3.0.0",
  "org.scalatest" %% "scalatest" % "3.0.0" % "test",
  "org.scalacheck" %% "scalacheck" % "1.12.5" % "test"
)
