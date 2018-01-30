name := """traccar"""

version := "1.0"

lazy val root = (project in file(".")).enablePlugins(PlayJava)

scalaVersion := "2.11.11"

libraryDependencies ++= Seq(
    javaCore,
    javaWs,
    "net.coobird" % "thumbnailator" % "0.4.8",
    "org.eclipse.persistence" % "eclipselink" % "2.6.2",
    "com.traccar" % "entities" % "1.0.0",
    "com.traccar" % "json-jpa-query" % "1.0.0",
    "com.traccar" % "positions" % "1.0.0",
    "mysql" % "mysql-connector-java" % "5.1.16",
    cache,
    filters,
    javaJpa
)

resolvers += Resolver.mavenLocal
resolvers += Resolver.jcenterRepo