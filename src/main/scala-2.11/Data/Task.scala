package Data

case class StartEndTime(val start: Integer, val end: Integer)
case class Task(val name: String,
                var buildStatus: BuildStatus = BuildStatus.NotBuilt,
                var startEndTime: Option[StartEndTime] = None,
                val dependencies: List[Task]) {

  def build(previous: Option[Task], cost: Int): Unit = {
    val startTime: Int = previous match {
      case Some(prev) =>
        prev.startEndTime match {
          case Some(prevTime) => prevTime.end
          case None => 0
        }
      case None => 0
    }

    val endTime: Int = startTime + cost
    this.startEndTime = Some(
      StartEndTime(
        startTime,
        endTime
      )
    )

    this.buildStatus = BuildStatus.Built
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
