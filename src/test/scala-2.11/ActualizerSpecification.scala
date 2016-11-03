import java.util.LinkedList

import Data.{BuildStatus, MachineType, Task, TaskQueue}
import Graph.Actualizer
import org.scalacheck.{Arbitrary, Gen, Prop, Properties}

class ActualizerSpecification extends Properties("Actualizer") {
  import Generators.JavaCollections._

  // Arbitrary Task Generation
  val genTask: Gen[Task] = Gen.wrap {
    Task(1, BuildStatus.NotBuilt, None, List[Int]())
  }

  val genLBQ = Gen.containerOf[LinkedList, Task](genTask)
  val genTaskQueue: Gen[TaskQueue] = for {
    tasks <- genLBQ
  } yield TaskQueue(tasks, MachineType.Small)
  val arbTaskQueue = Arbitrary(genTaskQueue)

  implicit val genDAG: Arbitrary[Array[TaskQueue]] = Arbitrary(
    Gen.containerOfN[Array, TaskQueue](1, genTaskQueue))

  property("makeSchedule() returns a list of built tasks") = Prop.forAll {
    taskList: Array[TaskQueue] =>
      val builtTasks: Array[Task] = Actualizer.makeSchedule(taskList)

      builtTasks.length == builtTasks.count { t: Task =>
        t.buildStatus == BuildStatus.Built
      }
  }
}
