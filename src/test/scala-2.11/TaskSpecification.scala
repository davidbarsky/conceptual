import org.scalacheck.{Properties, Prop}

import Data.{BuildStatus, Readiness, StartEndTime, Task}

object IncompleteDependencyListSpecification extends Properties("Task") {
  import Generators.Tasks.WithIncompleteDependencies._

  property("buildReadiness == Readiness.NotReady if dependencies aren't built") =
    Prop.forAll { t: Task =>
      t.buildReadiness == Readiness.NotReady
    }
}

object EmptyDependencyListSpecification extends Properties("Task") {
  import Generators.Tasks.WithNoDependencies._

  property("buildReadiness == Readiness.Ready if it has no dependencies") =
    Prop.forAll { t: Task =>
      t.buildReadiness == Readiness.Ready
    }
}

object BuildableTaskSpecification extends Properties("Task") {
  import Generators.Tasks.WithCompleteDependencies._

  property("build() builds a task") = Prop.forAll { current: Task =>
    val startEnd: StartEndTime = StartEndTime(0, 1)
    val previous = Task(1, BuildStatus.Built, Some(startEnd), List[Int]())

    current.buildStatus == BuildStatus.NotBuilt

    // building here because Actualizer.makeSchedule() normally would
    // handle building.
    current.build(Some(previous), 1)

    current.buildStatus == BuildStatus.Built
    current.startEndTime.get.end == 2
  }
}
