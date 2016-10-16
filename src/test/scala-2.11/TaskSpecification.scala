import Data.{Task, BuildStatus, Readiness}
import org.scalacheck.{Gen, Properties}
import org.scalacheck.Prop.forAll
import org.scalacheck.Arbitrary

object IncompleteDependencyListSpecification extends Properties("Task") {
  val incompleteTask: Task = {
    Task("A", BuildStatus.NotBuilt, None, List[Task]())
  }

  val genNotReadyTask: Gen[Task] = for {
    name <- Gen.oneOf("A", "B", "C")
    startEndTime <- Gen.wrap(None)
    dependencies <- Gen.nonEmptyListOf(incompleteTask)
  } yield Task(name, BuildStatus.NotBuilt, startEndTime, dependencies)
  implicit val arbTask = Arbitrary(genNotReadyTask)

  property("Task is not ready if its dependencies are not built") = forAll {
    t: Task =>
      t.buildReadiness == Readiness.NotReady
  }
}

object EmptyDependencyListSpecification extends Properties("Task") {
  val genReadyTask: Gen[Task] = for {
    name <- Gen.oneOf("A", "B", "C")
    startEndTime <- Gen.wrap(None)
  } yield Task(name, BuildStatus.NotBuilt, startEndTime, List[Task]())
  implicit val arbTask = Arbitrary(genReadyTask)

  property("Task is ready if it has no dependencies") = forAll { t: Task =>
    t.buildReadiness == Readiness.Ready
  }
}

object BuildableTaskSpecification extends Properties("Task") {
  val builtTask: Task = {
    Task("A", BuildStatus.Built, None, List[Task]())
  }

  val genBuildableTask: Gen[Task] = for {
    name <- Gen.oneOf("A", "B", "C")
    startEndTime <- Gen.wrap(None)
    dependencies <- Gen.nonEmptyListOf(builtTask)
  } yield Task(name, BuildStatus.NotBuilt, startEndTime, dependencies)
  implicit val arbTask = Arbitrary(genBuildableTask)

  property("Task is buildable") = forAll { t: Task =>
    t.buildReadiness == Readiness.Ready
  }
}
