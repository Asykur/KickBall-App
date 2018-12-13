package com.asykurkhamid.kickball.adapter

import org.junit.Test

import org.junit.Assert.*
import java.text.SimpleDateFormat

class DNHolderTest {

    @Test
    fun bindData() {
        val inFormat = SimpleDateFormat("yyyy-MM-dd")
        val dates = inFormat.parse("2018-08-24")
        val outFormat = SimpleDateFormat("EEEE, dd MMM yyyy")
        val event = outFormat.format(dates)

        val time = "16:15:00+00:00".substring(0,5)

        assertEquals("Friday, 24 Aug 2018",event)
        assertEquals("16:15",time)
    }
}