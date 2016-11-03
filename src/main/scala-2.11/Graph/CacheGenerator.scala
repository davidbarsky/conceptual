package Graph

import Data.{BuildStatus, Task}
import info.rmarcus.ggen4j.graph.Vertex

import scala.collection.JavaConversions._
import java.util.Set

import scala.collection.mutable.ListBuffer
import scala.collection.mutable.HashMap

object CacheGenerator {
  def makeCache(sources: List[Vertex]): HashMap[Int, Task] = {
    var table: HashMap[Int, Task] = HashMap[Int, Task]()

    def dfs(vertex: Vertex): Unit = {
      def getDependencies(vertices: Set[Vertex]): List[Int] = {
        val list = ListBuffer[Int]()
        for (vertex: Vertex <- vertices) {
          list.add(vertex.getID)
        }
        return list.toList
      }

      val dependencies: List[Int] = getDependencies(vertex.getParents.keySet())
      val task = Task(vertex.getID, BuildStatus.NotBuilt, None, dependencies)
      table += task.ID -> task

      for (child <- sources) {
        if (!table.containsKey(child.getID)) {
          dfs(child)
        }
      }
    }

    for (vertex <- sources) {
      dfs(vertex)
      for (child <- vertex.getChildren().keySet()) {
        if (!table.containsKey(child.getID)) {
          dfs(child)
        }
      }
    }

    return table
  }
}
