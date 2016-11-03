import Graph.{Actualizer, CacheGenerator, GGenWrapper, TaskQueueGenerator}
import Data.Task
import info.rmarcus.ggen4j.graph.Vertex

import scala.collection.mutable.HashMap

object Main {
  def main(args: Array[String]): Unit = {
    val sources: List[Vertex] = GGenWrapper.getSources()
    val tasks = TaskQueueGenerator.makeTaskQueues(sources)
    val cache: HashMap[Int, Task] = CacheGenerator.makeCache(sources)

    val actualizer = new Actualizer(cache);
    tasks.foreach(t => println(t.tasks))
    println(actualizer.makeSchedule(tasks))
  }
}
