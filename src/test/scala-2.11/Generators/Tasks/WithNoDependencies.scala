package Generators.Tasks

import Data.{BuildStatus, Task}
import org.scalacheck.{Arbitrary, Gen}

object WithNoDependencies {
  val genReadyTask: Gen[Task] = for {
    name <- Gen.oneOf("A", "B", "C")
    startEndTime <- Gen.wrap(None)
  } yield Task(name, BuildStatus.NotBuilt, startEndTime, List[Task]())
  implicit val arbTask = Arbitrary(genReadyTask)
}
