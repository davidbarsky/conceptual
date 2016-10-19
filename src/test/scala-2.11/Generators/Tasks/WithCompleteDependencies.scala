package Generators.Tasks

import Data.{BuildStatus, StartEndTime, Task}
import org.scalacheck.{Arbitrary, Gen}

object WithCompleteDependencies {
  val startEnd: StartEndTime = StartEndTime(0, 10)

  val builtTask: Task = {
    Task("A", BuildStatus.Built, Some(startEnd), List[Task]())
  }

  val genBuildableTask: Gen[Task] = for {
    name <- Gen.oneOf("A", "B", "C")
    startEndTime <- Gen.wrap(None)
    dependencies <- Gen.nonEmptyListOf(builtTask)
  } yield Task(name, BuildStatus.NotBuilt, startEndTime, dependencies)
  implicit val arbTask = Arbitrary(genBuildableTask)
}
