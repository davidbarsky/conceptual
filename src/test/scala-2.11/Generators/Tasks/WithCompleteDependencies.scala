package Generators.Tasks

import Data.{BuildStatus, StartEndTime, Task}
import org.scalacheck.{Arbitrary, Gen}

object WithCompleteDependencies {
  val startEnd: StartEndTime = StartEndTime(0, 10)

  val builtTask: Task = {
    Task(1, BuildStatus.Built, Some(startEnd), List[Int]())
  }

  val genBuildableTask: Gen[Task] = for {
    id <- Gen.choose(1, 100)
    startEndTime <- Gen.wrap(None)
    dependencies <- Gen.listOf(Gen.choose(1, 5))
  } yield Task(id, BuildStatus.NotBuilt, startEndTime, dependencies)
  implicit val arbTask = Arbitrary(genBuildableTask)
}
