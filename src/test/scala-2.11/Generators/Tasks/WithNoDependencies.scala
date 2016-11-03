package Generators.Tasks

import Data.{BuildStatus, Task}
import org.scalacheck.{Arbitrary, Gen}

object WithNoDependencies {
  val genReadyTask: Gen[Task] = for {
    id <- Gen.choose(1, 100)
    startEndTime <- Gen.wrap(None)
  } yield Task(id, BuildStatus.NotBuilt, startEndTime, List[Int]())
  implicit val arbTask = Arbitrary(genReadyTask)
}
