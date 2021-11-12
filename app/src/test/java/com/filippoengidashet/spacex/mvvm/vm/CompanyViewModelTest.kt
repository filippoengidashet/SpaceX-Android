package com.filippoengidashet.spacex.mvvm.vm

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.filippoengidashet.spacex.mvvm.model.entity.bean.CompanyData
import com.filippoengidashet.spacex.mvvm.model.entity.bean.CompanyInfoBean
import com.filippoengidashet.spacex.mvvm.model.entity.bean.ResultBean
import com.filippoengidashet.spacex.mvvm.model.mapper.SpaceXMapper
import com.filippoengidashet.spacex.mvvm.model.repository.SpaceXRepository
import com.filippoengidashet.spacex.mvvm.model.source.api.SpaceXApi
import com.filippoengidashet.spacex.mvvm.model.source.local.SpaceXLocal
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

/**
 * @author Filippo 12/11/2021
 */
@RunWith(MockitoJUnitRunner::class)
class CompanyViewModelTest {

    @get:Rule
    val testInstantTaskExecutorRule: TestRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var repository: SpaceXRepository

    @Mock
    private lateinit var localSource: SpaceXLocal

    @Mock
    private lateinit var apiSource: SpaceXApi

    @Mock
    private lateinit var mapper: SpaceXMapper

    private lateinit var viewModel: CompanyViewModel

    @Before
    fun setUp() {
        viewModel = CompanyViewModel(repository)
    }

    @ExperimentalCoroutinesApi
    @Test
    fun testLoadSuccess() {
        val companyYear = 2002
        val info = CompanyInfoBean(
            name = "Company Name",
            founder = "Founder Name",
            year = companyYear,
            employees = 1234321,
            launchSites = 5,
            valuation = 123456789L
        )
        val f = flow<ResultBean<CompanyData>> {
            emit(
                ResultBean.success(CompanyData(info, emptyList()))
            )
        }
        runBlockingTest {
            Mockito.doReturn(f)
                .`when`(repository)
                .getCompanyDataFlow()

            viewModel.getCompanyLiveData().observeForever { result ->
                val data = (result as ResultBean.Succeeded).data
                Assert.assertEquals(data.info.year, companyYear)
            }
        }

        viewModel.load()
    }

    @ExperimentalCoroutinesApi
    @Test
    fun testLoadFailure() {
        val failureMessage = "Failed for xyz..."
        val f = flow<ResultBean<CompanyData>> {
            emit(
                ResultBean.failure(failureMessage)
            )
        }
        runBlockingTest {
            Mockito.doReturn(f)
                .`when`(repository)
                .getCompanyDataFlow()

            viewModel.getCompanyLiveData().observeForever { result ->
                val errorMsg = (result as ResultBean.Failed).error
                Assert.assertEquals(errorMsg, failureMessage)
            }
        }

        viewModel.load()
    }
}
