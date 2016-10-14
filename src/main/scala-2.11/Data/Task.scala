package Data


case class StartEndTime(val start: Integer, val end: Integer)
case class Task(val name: String,
                var startEndTime: Option[StartEndTime],
                var status: TaskStatus,
                val dependencies: List[Task]) {

  override def equals(obj: scala.Any): Boolean = {
    val other = obj match {
      case other: Task => other
      case _ => throw new ClassCastException
    }

    if (other.name == this.name) {
      true
    } else {
      false
    }
  }
}

