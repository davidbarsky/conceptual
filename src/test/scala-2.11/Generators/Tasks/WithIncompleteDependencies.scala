package Generators.Tasks

import Data.{BuildStatus, Task}
import org.scalacheck.{Arbitrary, Gen}

object WithIncompleteDependencies {
  val incompleteTask: Task = {
    Task(1, BuildStatus.NotBuilt, None, List[Int]())
  }

  val taskWithDependencies: Gen[Task] = for {
    id <- Gen.choose(1, 100)
    startEndTime <- Gen.wrap(None)
    dependencies <- Gen.listOf(Gen.choose(1, 5))
  } yield Task(id, BuildStatus.NotBuilt, startEndTime, dependencies)
  implicit val arbitraryTask = Arbitrary(taskWithDependencies)
}
