import java.util.LinkedList

import org.scalacheck.Properties
import org.scalacheck.util.Buildable
import org.scalacheck.{Arbitrary, Gen}
import org.scalacheck.Prop.forAll

import scala.collection._
import scala.collection.JavaConverters._
import language.implicitConversions
import Data._

import scala.collection.mutable.Builder

object TaskQueueSpecification extends Properties("TaskQueue") {

  // Built using
  // http://stackoverflow.com/questions/39704941/scalacheck-new-buildable-instances
  implicit def buildableLinkedList[T]: Buildable[T, LinkedList[T]] =
    new Buildable[T, LinkedList[T]] {
      override def builder = new Builder[T, LinkedList[T]] {
        val lbq = new LinkedList[T]
        def +=(t: T) = {
          lbq.add(t)
          this
        }
        def clear() = lbq.clear()
        def result = lbq
      }
    }
  implicit def LinkedListTraversable[T](lbq: LinkedList[T]): Traversable[T] = {
    lbq.asScala
  }

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

  property("TaskQueue.take() take buildable tasks") = forAll {
    taskQueue: TaskQueue =>
      val task = taskQueue.take()
      task match {
        case Some(t: Task) => t.buildReadiness == Readiness.Ready
        case None => true == true
      }
  }
}
