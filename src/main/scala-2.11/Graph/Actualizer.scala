package Graph

import java.util.ArrayList

import scala.util.control.Breaks.{break, breakable}
import Data.{Task, TaskQueue, TaskStatus}

object Actualizer {
  def makeSchedule(tasks: List[TaskQueue]): Unit = {
    val logicalSchedule: ArrayList[Task] = new ArrayList[Task]()
    var previous: Option[Task] = Option.empty

    for (queue: TaskQueue <- tasks) {
      breakable {
        val task: Task = queue.tasks.peek()
        if (this.filterIncompleteDependencies(task.dependencies)) {
          val task: Task = queue.tasks.take()
          logicalSchedule.add(task)
        } else {
          break()
        }
      }
    }
  }

  def filterIncompleteDependencies(dependencies: List[Task]): Boolean = {
    for (task: Task <- dependencies) {
      if (task.status == TaskStatus.Incomplete) {
        return false
      }
    }

    return true
  }
}
