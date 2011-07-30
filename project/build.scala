import sbt._
import Keys._

import implicitly.LS.{ls, tags, description}

object Lsb extends Build {

   def commonSettings: Seq[Setting[_]] = Defaults.defaultSettings ++ Seq(
    organization := "me.lessis",
    version := "0.1.0"
   ) ++ implicitly.LS.lsSettings

   lazy val root = Project("root", file("."),
                           settings = commonSettings).aggregate(a, b, c)

   lazy val a = Project("a", file("a"),
                      settings = commonSettings ++ Seq(
                        name := "a",
                        tags in ls := Some(Seq("foo", "bar")),
                        description in ls := Some("My way cool concurrency library")
                      ))

   lazy val b = Project("b", file("b"),
                      settings = commonSettings ++ Seq(
                        name := "b"
                      ))

   lazy val c = Project("c", file("c"),
                      settings = commonSettings ++ Seq(
                        name := "c"
                      ))

}
