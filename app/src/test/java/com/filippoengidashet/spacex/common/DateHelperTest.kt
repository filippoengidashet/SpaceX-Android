package com.filippoengidashet.spacex.common

import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import java.util.*

/**
 * @author Filippo 12/11/2021
 */
class DateHelperTest {

    private val calendar = Calendar.getInstance(TimeZone.getTimeZone("UTC"))
    private lateinit var dateHelper: DateHelper

    @Before
    fun setUp() {
        dateHelper = DateHelper(calendar)
    }

    @Test
    fun testGetYearRange() {
        calendar.set(Calendar.YEAR, 2021)
        val yearRange = dateHelper.getYearRange(1900)
        val size = yearRange.size
        Assert.assertEquals(size, 122)
        Assert.assertEquals(yearRange[0], 2021)
        Assert.assertEquals(yearRange[size -1], 1900)
    }

    @Test
    fun testFormatDays() {
        val sinceMillis = 1222643700000
        val fromMillis = 1638316800000
        val (label1, days1) = dateHelper.formatDays(sinceMillis)
        val (label2, days2) = dateHelper.formatDays(fromMillis)
        Assert.assertEquals(label1, "since")
        Assert.assertEquals(days1, 4792)
        Assert.assertEquals(label2, "from")
        Assert.assertEquals(days2, 18)
    }

    @After
    fun tearDown() {
        //cleanup
    }
}