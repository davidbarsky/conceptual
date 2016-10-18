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

libraryDependencies ++= Seq(
  // Last stable release
  "org.scalanlp" %% "breeze" % "0.12",

  // Native libraries are not included by default. add this if you want them (as of 0.7)
  // Native libraries greatly improve performance, but increase jar sizes.
  // It also packages various blas implementations, which have licenses that may or may not
  // be compatible with the Apache License. No GPL code, as best I know.
  "org.scalanlp" %% "breeze-natives" % "0.12",

  // The visualization library is distribute separately as well.
  // It depends on LGPL code
  "org.scalanlp" %% "breeze-viz" % "0.12"
)
