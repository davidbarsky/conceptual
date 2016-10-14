import Data.{Task, TaskStatus}
import Graph.Actualizer
import org.scalatest.FunSuite
import org.scalatest.BeforeAndAfter
import scala.collection.immutable.List
import scala.collection.mutable.MutableList

class ActualizerTest extends FunSuite with BeforeAndAfter {
  val incompleteDependencies: List[Task] = makeIncompleteDependencies()
  val completeDependencies: List[Task] = makeCompletedDependenices()

  test("filterIncompleteDependencies rejects a task with incomplete dependencies") {
    assert(Actualizer.filterIncompleteDependencies(incompleteDependencies) === false)
  }

  test("filterIncompleteDependencies accepts a task with completed dependencies") {
    assert(Actualizer.filterIncompleteDependencies(completeDependencies) === true)
  }

  private def makeIncompleteDependencies(): List[Task] = {
    val task = Task("a", None, TaskStatus.Incomplete, List[Task]())
    val list: MutableList[Task] = MutableList[Task]()

    for (i <- 1 to 10) {
      list += task
    }

    return list.toList
  }

  private def makeCompletedDependenices(): List[Task] = {
    val task = Task("a", None, TaskStatus.Complete, List[Task]())
    val list: MutableList[Task] = MutableList[Task]()

    for (i <- 1 to 10) {
      list += task
    }

    return list.toList
  }
}