import sbt._
import Keys._

object Build extends sbt.Build {
  import ls.Plugin._

  def commonSettings: Seq[Setting[_]] =
    Defaults.defaultSettings ++ Seq(
      organization := "me.lessis",
      version := "0.1.0"
    ) ++ lsSettings

  lazy val root = Project("root", file("."),
                           settings = commonSettings).aggregate(a, b, c)

  lazy val a = Project("a", file("a"),
                       settings = commonSettings ++ Seq(
                         name := "a",
                         (LsKeys.tags in Ls) := Seq("foo", "bar"),
                         (description in Ls) :=
                           "My way cool futures library"
                      ))

  lazy val b = Project("b", file("b"),
                      settings = commonSettings ++ Seq(
                        name := "unfiltered-netty-server",
                        (homepage in Ls) := Some(new java.net.URL(
                          "http://unfiltered.databinder.net/Unfiltered.html")),
                        (LsKeys.tags in Ls) := Seq("http"),
                        (description in Ls) :=
                          "Web 4.0, possibly even 5",
                        libraryDependencies += "net.databinder" %% "unfiltered-netty-server" % "0.5.0"
                      ))

  lazy val c = Project("c", file("c"),
                      settings = commonSettings ++ Seq(
                        name := "c",
                        (description in Ls) :=
                          "Like twitter but for lepidopterists",
                        resolvers += "less is" at "http://repo.lessis.me"
                      ))

}
