package Data

import java.util.ArrayList

case class StartEndTime(val start: Int, val end: Int)
case class Task(val ID: Int,
                var buildStatus: BuildStatus = BuildStatus.NotBuilt,
                var startEndTime: Option[StartEndTime] = None,
                val dependencies: List[Int]) {

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

  override def equals(obj: scala.Any): Boolean = {
    val other = obj match {
      case other: Task => other
      case _ => throw new ClassCastException
    }

    return other.ID == this.ID match {
      case true => true
      case false => false
    }
  }
}
