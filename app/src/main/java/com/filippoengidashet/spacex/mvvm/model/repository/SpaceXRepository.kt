package com.filippoengidashet.spacex.mvvm.model.repository

import com.filippoengidashet.spacex.mvvm.model.entity.bean.CompanyData
import com.filippoengidashet.spacex.mvvm.model.entity.bean.ResultBean
import com.filippoengidashet.spacex.mvvm.model.mapper.SpaceXMapper
import com.filippoengidashet.spacex.mvvm.model.source.api.SpaceXApi
import com.filippoengidashet.spacex.mvvm.model.source.local.SpaceXLocal
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.withContext
import javax.inject.Inject

/**
 * @author Filippo 09/11/2021
 */
class SpaceXRepository @Inject constructor(
    private val localSource: SpaceXLocal,
    private val apiSource: SpaceXApi,
    private val mapper: SpaceXMapper,
) {

    suspend fun getCompanyDataFlow(): Flow<ResultBean<CompanyData>> {
        return flow {
            var cacheDataNotified = false
            try {
                val savedCompanyData = withContext(Dispatchers.IO) {
                    val savedInfo = localSource.getInfo()
                    val savedLaunches = localSource.getLaunches()
                    return@withContext savedInfo?.let {
                        CompanyData(it, savedLaunches)
                    }
                }
                if (savedCompanyData != null) {
                    cacheDataNotified = true
                    emit(ResultBean.success(savedCompanyData))
                }
                val companyInfoBean = mapper.mapCompanyInfo(apiSource.getCompanyInfo())
                val launches = mapper.mapRocketLaunches(apiSource.getLaunches(), apiSource.getRockets())
                withContext(Dispatchers.IO) {
                    localSource.deleteAll()
                    localSource.save(companyInfoBean)
                    localSource.save(launches)
                }
                emit(ResultBean.Succeeded(CompanyData(companyInfoBean, launches)))
            } catch (e: Exception) {
                if (!cacheDataNotified) {
                    emit(ResultBean.failure(e.message))
                }
            }
        }
    }
}
