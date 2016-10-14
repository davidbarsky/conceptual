package Data

import enumeratum._

sealed trait TaskStatus extends EnumEntry
object TaskStatus extends Enum[TaskStatus] {
  val values = findValues

  case object Complete extends TaskStatus
  case object Incomplete extends TaskStatus
}

sealed trait MachineType extends EnumEntry
object MachineType extends Enum[MachineType] {
  val values = findValues

  case object Small extends MachineType
  case object Large extends MachineType
}

