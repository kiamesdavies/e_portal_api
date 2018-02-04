name := """e-portal"""

version := "1.0"

lazy val root = (project in file(".")).enablePlugins(PlayJava)

scalaVersion := "2.11.11"

libraryDependencies ++= Seq(
    javaCore,
    javaWs,
    "net.coobird" % "thumbnailator" % "0.4.8",
    "org.eclipse.persistence" % "eclipselink" % "2.6.2",
    "com.traccar" % "entities" % "1.0.0",
    "com.portal" % "web_mvc" % "1.0.0",
    "com.portal" % "user_management" % "1.0.0",
    "com.portal" % "commons" % "1.0.0",
    "com.portal" % "actors" % "1.0.0",
    "com.portal" % "binder" % "1.0.0",
    "com.portal" % "applications" % "1.0.0",
    "com.portal" % "configuration" % "1.0.0",
    "com.portal" % "payment" % "1.0.0",
    "org.postgresql" % "postgresql" % "9.4.1209.jre7",
    "io.swagger" %% "swagger-play2" % "1.5.2-SNAPSHOT",
    "io.swagger"         % "swagger-parser"             % "1.0.20",
    "io.swagger" % "swagger-annotations" % "1.5.9",
    "org.flywaydb" %% "flyway-play" % "3.0.1",
    "com.enragedginger" %% "akka-quartz-scheduler" % "1.6.0-akka-2.4.x",
    "jaxen" % "jaxen" % "1.1.6",
    "com.opencsv" % "opencsv" % "3.8",
    "org.jdom" % "jdom2" % "2.0.6",

    "com.openhtmltopdf" % "openhtmltopdf-core" % "0.0.1-RC12",
    "com.openhtmltopdf" % "openhtmltopdf-pdfbox" % "0.0.1-RC12",
    cache,
    filters,
    javaJpa
)

resolvers += Resolver.mavenLocal
resolvers += Resolver.jcenterRepo