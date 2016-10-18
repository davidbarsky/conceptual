package Graph

import scala.collection.mutable.ArrayBuffer

import scala.util.control.Breaks.{break, breakable}
import Data.{Task, TaskQueue}

object Actualizer {
  def makeSchedule(tasks: Array[TaskQueue]): Array[Task] = {
    val logicalSchedule: ArrayBuffer[Task] = ArrayBuffer[Task]()
    var previous: Option[Task] = Option.empty

    for (queue: TaskQueue <- tasks) {
      breakable {
        val task: Option[Task] = queue.take()
        task match {
          case Some(task) => {
            logicalSchedule += (task)
            previous = Some(task)
          }
          case None => break()
        }
      }
    }
    return logicalSchedule.toArray
  }
}
