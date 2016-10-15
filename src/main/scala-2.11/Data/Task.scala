package Data

case class StartEndTime(val start: Integer, val end: Integer)
case class Task(val name: String,
                var buildStatus: BuildStatus = BuildStatus.NotBuilt,
                var startEndTime: Option[StartEndTime] = None,
                val dependencies: List[Task]) {

  def build(previous: Task, cost: Int): Unit = {
    this.buildStatus = BuildStatus.Built
    this.startEndTime = Some(
      StartEndTime(
        previous.startEndTime.get.start,
        cost
      ))
  }

  var buildReadiness: Readiness = {
    val hasUnbuilt = this.dependencies.exists { (t: Task) =>
      t.buildStatus == BuildStatus.NotBuilt
    }

    hasUnbuilt match {
      case true => Readiness.NotReady
      case false => Readiness.Ready
    }
  }

  override def equals(obj: scala.Any): Boolean = {
    val other = obj match {
      case other: Task => other
      case _ => throw new ClassCastException
    }

    return other.name == this.name match {
      case true => true
      case false => false
    }
  }
}
