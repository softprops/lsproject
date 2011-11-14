import sbt._
import Keys._

object Build extends sbt.Build {
  import ls.Plugin._

  def lsBuildSettings: Seq[Setting[_]] = lsSettings

  def commonSettings: Seq[Setting[_]] =
    Defaults.defaultSettings ++ Seq(
      organization := "me.lessis",
      version := "0.1.3",
      crossScalaVersions := Seq("2.8.0", "2.8.1", "2.8.2",
                              "2.9.0", "2.9.0-1", "2.9.1")
    ) ++ lsBuildSettings ++ Seq(
      //(LsKeys.host in LsKeys.lsync) := "http://localhost:5000"
    )

  lazy val root = Project("root", file("."),
    settings = commonSettings ++ Seq(
      (LsKeys.writeVersion in LsKeys.lsync) := { }
    )
  ).aggregate(a, b, c)

  lazy val a = Project("a", file("a"),
    settings = commonSettings ++ Seq(
      name := "more-futurer",
      (LsKeys.tags in LsKeys.lsync) := Seq("foo", "bar", "async"),
      (description in LsKeys.lsync) :=
        "My super cool futures library (did update)"
    )
  )

  lazy val b = Project("b", file("b"),
    settings = commonSettings ++ Seq(
      name := "ba-ba-boom",
      (homepage in LsKeys.lsync) := Some(
        new java.net.URL("http://somesite.com/ba-ba-boom")),
      (LsKeys.tags in LsKeys.lsync) := Seq("explosive"),
      (description in LsKeys.lsync) :=
        "This library is not allowed to enter traffic tunnels (did update)",
      (homepage in LsKeys.lsync) := Some(url("http://somesite.com")),
      (licenses in LsKeys.lsync) := Seq(
        ("MIT", url("https://somesite.com/LICENSE"))
      ),
      libraryDependencies ++= Seq(
        "net.databinder" %% "unfiltered-netty-server" % "0.5.0",
        "net.databinder" %% "dispatch" % "0.8.5" % "test"
      )
    )
  )

  lazy val c = Project("c", file("c"),
    settings = commonSettings ++ Seq(
      name := "moth-talk",
      (description in LsKeys.lsync) :=
      "Like twitter but for lepidopterists (did update)",
      resolvers += "less is" at "http://repo.lessis.me"
    )
  )
}
