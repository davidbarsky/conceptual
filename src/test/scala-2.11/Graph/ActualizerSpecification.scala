import java.util.{LinkedList}

import org.scalacheck.{Arbitrary, Gen, Prop, Properties}
import Data.{BuildStatus, MachineType, StartEndTime, Task, TaskQueue}
import Graph.Actualizer

class ActualizerSpecification extends Properties("Actualizer") {
  import JavaCollectionGenerator._

  // Arbitrary Task Generation
  val genTask: Gen[Task] = Gen.wrap {
    Task("A", BuildStatus.NotBuilt, None, List[Task]())
  }

  val genLBQ = Gen.containerOf[LinkedList, Task](genTask)
  val genTaskQueue: Gen[TaskQueue] = for {
    tasks <- genLBQ
  } yield TaskQueue(tasks, MachineType.Small)
  val arbTaskQueue = Arbitrary(genTaskQueue)

  implicit val genDAG: Arbitrary[Array[TaskQueue]] = Arbitrary(
    Gen.containerOfN[Array, TaskQueue](1, genTaskQueue))

  property("makeSchedule() returns a list of built tasks") =
    Prop.forAll { taskList: Array[TaskQueue] =>
      val builtTasks: Array[Task] = Actualizer.makeSchedule(taskList)

      def isBuilt(t: Task): Boolean = {
        t.buildStatus == BuildStatus.Built
      }

      builtTasks.length == builtTasks.filterNot(isBuilt).length
    }
}
