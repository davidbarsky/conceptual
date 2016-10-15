package Data

import enumeratum._

sealed trait BuildStatus extends EnumEntry
object BuildStatus extends Enum[BuildStatus] {
  val values = findValues

  case object Built extends BuildStatus
  case object NotBuilt extends BuildStatus
}

sealed trait Readiness extends EnumEntry
object Readiness extends Enum[Readiness] {
  val values = findValues

  case object Ready extends Readiness
  case object NotReady extends Readiness
}

sealed trait MachineType extends EnumEntry
object MachineType extends Enum[MachineType] {
  val values = findValues

  case object Small extends MachineType
  case object Large extends MachineType
}
