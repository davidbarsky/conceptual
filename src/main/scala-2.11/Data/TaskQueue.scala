package Data

import java.util.concurrent.LinkedBlockingQueue

case class TaskQueue(val tasks: LinkedBlockingQueue[Task],
                     val machineType: TaskStatus)
