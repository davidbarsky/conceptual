package Graph

import scala.collection.mutable.ListBuffer
import scala.util.control.Breaks.{break, breakable}
import Data.{BuildStatus, Task, TaskQueue}

import scala.collection.mutable.{HashMap}

class Actualizer(val cache: HashMap[Int, Task]) {
  def makeSchedule(tasks: List[TaskQueue]): List[Task] = {
    val logicalSchedule: ListBuffer[Task] = ListBuffer[Task]()
    var previous: Option[Task] = Option.empty

    for (queue: TaskQueue <- tasks) {
      breakable {
        val task: Option[Task] = queue.take()

        task match {
          case Some(task) => {
            if (!isBuildable(task.ID)) {
              break()
            } else {
              task.build(previous, 1)
              updateCache(task)
              logicalSchedule += (task)
              previous = Some(task)
            }
          }
          case None => println("Can't build task: " + task)
        }
      }
    }
    logicalSchedule.toList
  }

  private def updateCache(task: Task): Unit = {
    cache += task.ID -> Task(task.ID,
                             BuildStatus.Built,
                             task.startEndTime,
                             task.dependencies)
  }

  private def isBuildable(id: Int): Boolean = {
    val task: Option[Task] = cache.get(id)

    task match {
      case None =>
        throw new RuntimeException("Cache does not have task of ID: " + id)
      case Some(t) => {
        if (t.dependencies.isEmpty) {
          return true
        }

        return t.dependencies.exists(i => {
          cache.get(i) match {
            case Some(dependency) =>
              dependency.buildStatus != BuildStatus.Built
            case None =>
              throw new RuntimeException(
                "Cache does not have dependency of ID: " + id)
          }
        })
      }
    }
  }
}
