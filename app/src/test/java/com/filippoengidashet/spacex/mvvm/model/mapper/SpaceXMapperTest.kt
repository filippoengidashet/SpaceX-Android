package com.filippoengidashet.spacex.mvvm.model.mapper

import com.filippoengidashet.spacex.mvvm.model.entity.dto.CompanyInfoResponse
import com.filippoengidashet.spacex.mvvm.model.entity.dto.LaunchesResponse
import com.filippoengidashet.spacex.mvvm.model.entity.dto.Links
import com.filippoengidashet.spacex.mvvm.model.entity.dto.RocketsResponse
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

/**
 * @author Filippo 12/11/2021
 */
@RunWith(MockitoJUnitRunner::class)
class SpaceXMapperTest {

    private lateinit var mapper: SpaceXMapper

    @Before
    fun setUp() {
        mapper = SpaceXMapper()
    }

    @Test
    fun testMapCompanyInfo() {
        val response = Mockito.mock(CompanyInfoResponse::class.java)
        Mockito.`when`(response.name).thenReturn("Test Name")
        Mockito.`when`(response.founder).thenReturn("Test Founder")
        Mockito.`when`(response.founded).thenReturn(2002)
        Mockito.`when`(response.employees).thenReturn(123)
        Mockito.`when`(response.launch_sites).thenReturn(5)
        Mockito.`when`(response.valuation).thenReturn(55555L)
        val info = mapper.mapCompanyInfo(response)

        Assert.assertEquals(info.launchSites, 5)
    }

    @Test
    fun testMapLaunches() {
        val launches: List<LaunchesResponse> = listOf(
            LaunchesResponse(
                Links(null, "link", "link", "link"),
                "123",
                "r_name",
                "r",
                true,
                1782872872,
                "2006-03-24T22:30:00.000Z"
            ),
            LaunchesResponse(
                Links(null, "link", "link", "link"),
                "125",
                "r_name",
                "r",
                true,
                1782872872,
                "2007-03-21T01:10:00.000Z"
            )
        )
        val rockets: List<RocketsResponse> = listOf(
            RocketsResponse("123", "r1_name", "r1_type"),
            RocketsResponse("125", "r2_name", "r2_type"),
        )

        val launchList = mapper.mapRocketLaunches(launches, rockets)
        Assert.assertEquals(launchList.size, 2)
    }
}
