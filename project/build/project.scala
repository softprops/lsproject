import sbt._

class Project(info: ProjectInfo) extends ParentProject(info) with implicitly.Listing {

  class ModuleListing(info: ProjectInfo) extends DefaultProject(info) with implicitly.Listing

  lazy val moduleA = project("moduleA", "Module A", new ModuleListing(_))

  lazy val moduleB = project("moduleB", "Module B", new ModuleListing(_))

  lazy val moduleC = project("moduleC", "Module C", new ModuleListing(_))

  // publishing
  override def managedStyle = ManagedStyle.Maven

  val publishTo = "Scala Tools Nexus" at
    "http://nexus.scala-tools.org/content/repositories/releases/"
}
