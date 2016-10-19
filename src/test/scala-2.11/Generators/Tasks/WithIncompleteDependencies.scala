package Generators.Tasks

import Data.{BuildStatus, Task}
import org.scalacheck.{Arbitrary, Gen}

object WithIncompleteDependencies {
  val incompleteTask: Task = {
    Task("A", BuildStatus.NotBuilt, None, List[Task]())
  }

  val taskWithDependencies: Gen[Task] = for {
    name <- Gen.oneOf("A", "B", "C")
    startEndTime <- Gen.wrap(None)
    dependencies <- Gen.nonEmptyListOf(incompleteTask)
  } yield Task(name, BuildStatus.NotBuilt, startEndTime, dependencies)
  implicit val arbitraryTask = Arbitrary(taskWithDependencies)
}
