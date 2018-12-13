package com.asykurkhamid.kickball.retrofit

import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify

//Test request
class ServiceFactoryTest{
    @Test
    fun testRequest(){
        val apiRepo = mock(ServiceFactory::class.java)
        apiRepo.getInitInstance().getNextData("4328")
        verify(apiRepo).getInitInstance().getNextData("4328")
    }
}