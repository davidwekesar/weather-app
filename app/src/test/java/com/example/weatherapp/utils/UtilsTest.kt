package com.example.weatherapp.utils

import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Test

class UtilsTest {

    @Test
    fun convert_epochTime_returnsDate() {
        val epochTime = 1647953820L
        val expectedResult = "Tue, 22 Mar, 2022"

        val date = convertToDate(epochTime)

        assertThat(date, `is`(expectedResult))
    }

//    @Test
//    fun convert_epochTime_returnsTime() {
//        val epochTime = 1647953820L
//        val expectedResult = "3:57 PM"
//
//        val time = convertToTime(epochTime)
//
//        assertThat(time, `is`(expectedResult))
//    }
}