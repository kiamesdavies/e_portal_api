name := "swagger-play2"
version := "1.5.2-SNAPSHOT"

checksums in update := Nil

scalaVersion:= "2.11.6"
crossScalaVersions := Seq("2.11.6", "2.11.7")

libraryDependencies ++= Seq(
  "org.slf4j"                      % "slf4j-api"                  % "1.7.16",
  "io.swagger"                     % "swagger-core"               % "1.5.8",
  "io.swagger"                    %% "swagger-scala-module"       % "1.0.2",
  "com.typesafe.play"             %% "routes-compiler"            % "2.5.1",
  "com.fasterxml.jackson.module"  %% "jackson-module-scala"       % "2.7.2",
  "com.typesafe.play"             %% "play-ebean"                 % "3.0.0"            % "test",
  "org.specs2"                    %% "specs2-core"                % "3.6.6"            % "test",
  "org.specs2"                    %% "specs2-mock"                % "3.6.6"            % "test",
  "org.specs2"                    %% "specs2-junit"               % "3.6.6"            % "test",
  "org.mockito"                    % "mockito-core"               % "1.10.19"          % "test")

mappings in (Compile, packageBin) ~= { _.filter(!_._1.getName.equals("logback.xml")) }


publishArtifact in Test := false
publishMavenStyle := true
pomIncludeRepository := { x => false }
credentials += Credentials(Path.userHome / ".ivy2" / ".credentials")
organization := "io.swagger"
lazy val root = (project in file(".")).enablePlugins(PlayScala)
