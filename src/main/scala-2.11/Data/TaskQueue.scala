package Data

import java.util.LinkedList

case class TaskQueue(val tasks: LinkedList[Task], val machineType: MachineType) {
  def take(): Option[Task] = {
    if (tasks.isEmpty) {
      return None
    }

    val task: Task = this.tasks.peek()
    task.buildReadiness match {
      case Readiness.Ready => {
        return Some(this.tasks.pop())
      }
      case Readiness.NotReady => return None
    }
  }
}
