package com.filippoengidashet.spacex.mvvm.model.source.api

import com.filippoengidashet.spacex.mvvm.model.AppConstants
import com.filippoengidashet.spacex.mvvm.model.entity.dto.CompanyInfoResponse
import com.filippoengidashet.spacex.mvvm.model.entity.dto.LaunchesResponse
import com.filippoengidashet.spacex.mvvm.model.entity.dto.RocketsResponse
import retrofit2.http.GET

/**
 * @author Filippo 09/11/2021
 */
interface SpaceXApi {

    @GET(AppConstants.Api.ENDPOINT_COMPANY)
    suspend fun getCompanyInfo() : CompanyInfoResponse

    @GET(AppConstants.Api.ENDPOINT_LAUNCHES)
    suspend fun getLaunches() : List<LaunchesResponse>

    @GET(AppConstants.Api.ENDPOINT_ROCKETS)
    suspend fun getRockets() : List<RocketsResponse>
}
