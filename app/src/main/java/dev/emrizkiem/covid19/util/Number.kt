package dev.emrizkiem.covid19.util

import java.text.NumberFormat
import java.text.SimpleDateFormat
import java.util.*

object Number {
    fun numberFormat(number: Int) = NumberFormat.getNumberInstance(Locale.getDefault()).format(number)

    fun extractDigit(number: String) = Regex("[^0-9]").replace(number, "").toInt()

    fun formatTime(time: Long) : String{
        val sdf = SimpleDateFormat("dd MMMM yyyy", Locale.getDefault())
        return sdf.format(Date(time))
    }
}