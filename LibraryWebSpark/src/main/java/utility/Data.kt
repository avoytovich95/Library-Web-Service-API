package utility

import com.fasterxml.jackson.databind.ObjectMapper
import spark.*
import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.TimeUnit

/**
 * Created by Alex on 3/22/2018.
 */
object Data {

    private val upper = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
    private val lower = upper.toLowerCase()
    private val digits = "0123456789"
    private val alphanum = upper + lower + digits

    fun randString(size: Int): String {
        var str = ""
        for (i in 1..size)
            str += alphanum[(Math.random() * alphanum.length).toInt()]
        return str
    }

    fun getDays(date: String): Long {
        val dateFormat = SimpleDateFormat("MMddyyyy")
        val checkout = dateFormat.parse(date)
        val current = Date()
        val diff = current.time - checkout.time

        return TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS)
    }

    fun getDate(): String {
        val cal = Calendar.getInstance()
        val mnth = cal.get(Calendar.MONTH) + 1
        val month = if (mnth < 10) "0$mnth"
                else mnth.toString() + ""

        val dt = cal.get(Calendar.DATE)
        val date = if (dt < 10) "0$dt"
                else dt.toString() + ""
        return month + date + cal.get(Calendar.YEAR)
    }
}