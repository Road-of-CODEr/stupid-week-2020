class Solution {
  fun solution(record: Array<String>): Array<String> {
    val map = mutableMapOf<String, String>()

    val loggerList = record.mapNotNull { str ->
      val (status, uid) = str.split(" ")
      when (status) {
        LoggerStatus.Enter.name -> {
          map[uid] = str.split(" ").last()
          Logger.enterLog(uid)
        }
        LoggerStatus.Leave.name -> {
          Logger.leaveLog(uid)
        }
        else -> {
          map[uid] = str.split(" ").last()
          null
        }
      }
    }
    return loggerList.map { map[it.uid] + it.toString() }.toTypedArray()
  }

  class Logger(
      val uid: String,
      private val loggerStatus: LoggerStatus
  ) {
    companion object {
      fun enterLog(uid: String): Logger {
        return Logger(uid, LoggerStatus.Enter)
      }

      fun leaveLog(uid: String): Logger {
        return Logger(uid, LoggerStatus.Leave)
      }
    }

    override fun toString(): String {
      val statusString = when (this.loggerStatus) {
        LoggerStatus.Enter -> "들어왔습니다."
        LoggerStatus.Leave -> "나갔습니다."
      }
      return "님이 $statusString"
    }
  }

  enum class LoggerStatus {
    Enter, Leave
  }
}