package Graph

import java.util.ArrayList

import scala.util.control.Breaks.{break, breakable}
import Data.{Task, TaskQueue}

object Actualizer {
  def makeSchedule(tasks: List[TaskQueue]): ArrayList[Task] = {
    val logicalSchedule: ArrayList[Task] = new ArrayList[Task]()
    var previous: Option[Task] = Option.empty

    for (queue: TaskQueue <- tasks) {
      breakable {
        val task: Option[Task] = queue.take()
        task match {
          case Some(task) => {
            logicalSchedule.add(task)
            previous = Some(task)
          }
          case None => break()
        }
      }
    }
    return logicalSchedule
  }
}
