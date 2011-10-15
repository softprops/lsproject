import sbt._
import Keys._

object Build extends sbt.Build {
  import ls.Plugin._

  def lsBuildSettings: Seq[Setting[_]] = lsSettings ++ Seq(
    (LsKeys.lsHost in Ls) := "http://localhost:5000"
  )

  def commonSettings: Seq[Setting[_]] =
    Defaults.defaultSettings ++ Seq(
      organization := "me.lessis",
      version := "0.1.3",
      crossScalaVersions := Seq("2.8.0", "2.8.1", "2.8.2",
                              "2.9.0", "2.9.0-1", "2.9.1")
    ) ++ lsBuildSettings

  lazy val root = Project("root", file("."),
                           settings = commonSettings).aggregate(a, b, c)

  lazy val a = Project("a", file("a"),
                       settings = commonSettings ++ Seq(
                         name := "more-futurer",
                         (LsKeys.tags in Ls) := Seq("foo", "bar", "async"),
                         (description in Ls) :=
                           "My super cool futures library"
                      ))

  lazy val b = Project("b", file("b"),
                      settings = commonSettings ++ Seq(
                        name := "unfiltered-netty-server",
                        (homepage in Ls) := Some(new java.net.URL(
                          "http://unfiltered.databinder.net/Unfiltered.html")),
                        (LsKeys.tags in Ls) := Seq("http", "web", "unfiltered"),
                        (description in Ls) :=
                          "Web 4.0, possibly even 5",
                        (homepage in Ls) := Some(url("http://unfiltered.databinder.net/Unfiltered.html")),
                        (licenses in Ls) := Seq(
                          ("MIT", url("https://github.com/unfiltered/unfiltered/blob/master/LICENSE"))
                         ),
                        libraryDependencies += "net.databinder" %% "unfiltered-netty-server" % "0.5.0"
                      ))

  lazy val c = Project("c", file("c"),
                      settings = commonSettings ++ Seq(
                        name := "moth-talk",
                        (description in Ls) :=
                          "Like twitter but for lepidopterists",
                        resolvers += "less is" at "http://repo.lessis.me"
                      ))

}
