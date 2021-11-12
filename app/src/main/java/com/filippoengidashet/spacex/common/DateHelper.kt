package com.filippoengidashet.spacex.common

import java.util.*
import java.util.concurrent.TimeUnit
import javax.inject.Inject

/**
 * @author Filippo 10/11/2021
 */
class DateHelper @Inject constructor(
    private val calendar: Calendar,
) {

    fun getYearRange(from: Int = 1900): List<Int> {
        val targetYear = calendar.get(Calendar.YEAR)
        val years = mutableListOf<Int>()
        for (year in targetYear downTo from) {
            years.add(year)
        }
        return years
    }

    fun formatDays(millis: Long): Pair<String, Long> {

        val nowMillis = calendar.timeInMillis
        val daysLabel = if (millis > nowMillis) "from" else "since"
        val days = TimeUnit.MILLISECONDS.toDays(Math.abs(millis - nowMillis))

        return Pair(daysLabel, days)
    }
}
