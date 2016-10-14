import Data.{StartEndTime, Task, TaskStatus}
import org.scalacheck.{Gen}
import org.scalacheck.Arbitrary._

object TaskGenerator {
  def startEndTime: Gen[StartEndTime] = for {
    start <- Gen.choose(0, 10)
    end <- Gen.choose(start, 10)
  } yield StartEndTime(start, end)

  def dependencies: Gen[List[Task]] = for {
    list <- Gen.listOf(incompleteTask)
  } yield list

  def incompleteTask: Gen[Task] = for {
    name <- Gen.alphaStr
  } yield Task(name, None, TaskStatus.Incomplete, dependencies.sample.get)

  val incompleteDependencies = Gen.containerOf[List, Task](incompleteTask)
}

