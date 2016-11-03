package Data

import java.util.LinkedList

class TaskQueue(val tasks: LinkedList[Task], val machineType: MachineType) {
  def take(): Option[Task] = {
    if (tasks.isEmpty) {
      return None
    }

    val task: Task = this.tasks.peek()

    task == null match {
      case false => Some(task)
      case true => None
    }
  }
}
