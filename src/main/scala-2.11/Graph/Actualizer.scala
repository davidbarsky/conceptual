package Graph

import java.util.ArrayList

import scala.util.control.Breaks.{break, breakable}
import Data.{Task, TaskQueue, Readiness}

object Actualizer {
  def makeSchedule(tasks: List[TaskQueue]): Unit = {
    val logicalSchedule: ArrayList[Task] = new ArrayList[Task]()
    var previous: Option[Task] = Option.empty

    for (queue: TaskQueue <- tasks) {
      breakable {
        val task: Task = queue.tasks.peek()
        task.buildReadiness match {
          case Readiness.Ready => {
            val task: Task = queue.tasks.take()
            logicalSchedule.add(task)
          }
          case _ => break()
        }
      }
    }
  }
}
