
import java.text.SimpleDateFormat
import java.util.{Date, TimeZone, Calendar}

object CalendarTest {
  def main(args: Array[String]) {
    /*
    val ids = TimeZone.getAvailableIDs.toList
    ids foreach { id =>
      println("TZ: " + id)
    }
    */

    val cal: Calendar = Calendar.getInstance(TimeZone.getTimeZone("UTC"))
    val cal2: Calendar = Calendar.getInstance(TimeZone.getTimeZone("PST"))
    val cal3: Calendar = Calendar.getInstance()

    println("UTC in ms: " + cal.getTimeInMillis + ", " + cal.toString)
    println("PST in ms: " + cal2.getTimeInMillis + ", " + cal2.toString)
    println("local in ms: " + cal3.getTimeInMillis + ", " + cal3.toString)

    println("-----------------------------")
    val date = "2014-09-16"
    val fmt: SimpleDateFormat = new SimpleDateFormat("yyyy-MM-dd")
    val day: Date = fmt.parse(date);
    println("Date.getTime: " + day.getTime() + ", " + day.toString)

    val fmt2: SimpleDateFormat = new SimpleDateFormat("yyyy-MM-dd")
    fmt2.setTimeZone(TimeZone.getTimeZone("UTC"))
    val day2: Date = fmt2.parse(date);
    println("UTC Date.getTime: " + day2.getTime() + ", " + day2.toString)

    println("-----------------------------")
    val cal4: Calendar = Calendar.getInstance(TimeZone.getTimeZone("UTC"))
    cal4.clear
    cal4.setTime(day2)
    println("UTC in ms: " + cal4.getTimeInMillis + ", " + cal4.toString)

    println("-----------------------------")
    val cal5: Calendar = Calendar.getInstance(TimeZone.getTimeZone("UTC"))
    cal5.set(Calendar.HOUR, 0)
    cal5.set(Calendar.SECOND, 0)
    cal5.set(Calendar.MILLISECOND, 0)
    println("UTC in ms: " + cal5.getTimeInMillis + ", " + cal5.toString)
    cal5.add(Calendar.DATE, -1)
    println("UTC in ms: " + cal5.getTimeInMillis + ", " + cal5.toString)
  }
}
