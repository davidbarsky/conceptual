import java.util.LinkedList

import org.scalacheck.Properties
import org.scalacheck.{Arbitrary, Gen}
import org.scalacheck.Prop.forAll

import language.implicitConversions
import Data._

object TaskQueueSpecification extends Properties("TaskQueue") {
  import JavaCollectionGenerator._

  val incompleteTask: Task = {
    Task("A", BuildStatus.NotBuilt, None, List[Task]())
  }

  val genReadyTask: Gen[Task] = for {
    name <- Gen.oneOf("A", "B", "C")
    startEndTime <- Gen.wrap(None)
    dependencies <- Gen.nonEmptyListOf(incompleteTask)
  } yield Task(name, BuildStatus.NotBuilt, startEndTime, dependencies)
  implicit val arbTask = Arbitrary(genReadyTask)

  val genLBQ = Gen.containerOf[LinkedList, Task](genReadyTask)

  val genTaskQueue: Gen[TaskQueue] = for {
    tasks <- genLBQ
  } yield TaskQueue(tasks, MachineType.Small)
  implicit val arbTaskQueue = Arbitrary(genTaskQueue)

  property("take() returns buildable tasks") = forAll {
    taskQueue: TaskQueue =>
      val task = taskQueue.take()
      task match {
        case Some(t: Task) => t.buildReadiness == Readiness.Ready
        case None => true == true
      }
  }
}
