import java.util.LinkedList

import org.scalacheck.{Arbitrary, Gen, Prop, Properties}

import language.implicitConversions
import Data._

object TaskQueueSpecification extends Properties("TaskQueue") {
  import Generators.JavaCollections._

  val genReadyTask: Gen[Task] = for {
    id <- Gen.oneOf(1, 2, 3)
    startEndTime <- Gen.wrap(None)
    dependencies <- Gen.listOf(Gen.choose(1, 5))
  } yield Task(id, BuildStatus.NotBuilt, startEndTime, dependencies)
  implicit val arbTask = Arbitrary(genReadyTask)

  val taskQueue = Gen.containerOf[LinkedList, Task](genReadyTask)

  val genTaskQueue: Gen[TaskQueue] = for {
    tasks <- taskQueue
  } yield TaskQueue(tasks, MachineType.Small)
  implicit val arbTaskQueue = Arbitrary(genTaskQueue)

  property("take() returns buildable tasks") = Prop.forAll {
    taskQueue: TaskQueue =>
      val task = taskQueue.take()
      task match {
        case Some(t: Task) => t.buildReadiness == Readiness.Ready
        case None => true == true
      }
  }
}
