package Graph

import Data.{BuildStatus, MachineType, Task, TaskQueue}
import info.rmarcus.ggen4j.graph.Vertex

import collection.mutable.{ListBuffer, Queue}
import collection.JavaConverters._
import java.util.{LinkedList, Random}

object TaskQueueGenerator {
  def makeTaskQueues(tasks: List[Vertex]): List[TaskQueue] = {
    val taskQueues = generateTaskQueues()

    val visited = new Queue[Vertex]()
    visited += tasks.take(1).head
    while (!visited.isEmpty) {
      var current = visited.dequeue()
      for (child: Vertex <- current.getChildren().keySet().asScala.toList) {
        if (!visited.contains(child)) {
          val task = Task(child.getID,
                          BuildStatus.NotBuilt,
                          None,
                          getDependencies(child))
          addToRandomTaskQueue(task, taskQueues)

          visited += child
        }
      }
    }

    return taskQueues
  }

  private def addToRandomTaskQueue(task: Task, taskQueues: List[TaskQueue]): Unit = {
    val random = new Random()
    val int = random.nextInt(5)

    taskQueues(int).tasks.add(task)
  }

  private def getDependencies(vertex: Vertex): List[Int] = {
    val listBuffer = ListBuffer[Int]()
    for (dependency: Vertex <- vertex.getParents.keySet().asScala.toList) {
      listBuffer += dependency.getID
    }

    return listBuffer.toList
  }

  private def generateTaskQueues(): List[TaskQueue] = {
    val taskQueues = ListBuffer[TaskQueue]()
    for (i <- 1 to 5) {
      taskQueues += new TaskQueue(new LinkedList[Task], MachineType.Small)
    }

    taskQueues.toList
  }
}
