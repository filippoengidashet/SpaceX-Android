package com.filippoengidashet.spacex.mvvm.model.mapper

import com.filippoengidashet.spacex.mvvm.model.entity.bean.CompanyInfoBean
import com.filippoengidashet.spacex.mvvm.model.entity.bean.Launch
import com.filippoengidashet.spacex.mvvm.model.entity.dto.CompanyInfoResponse
import com.filippoengidashet.spacex.mvvm.model.entity.dto.LaunchesResponse
import com.filippoengidashet.spacex.mvvm.model.entity.dto.RocketsResponse
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

/**
 * @author Filippo 09/11/2021
 */
class SpaceXMapper @Inject constructor() {

    companion object {
        private const val DATE_FORMAT = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'"
        private const val DATETIME_FORMAT = "yyyy-MM-dd 'at' HH:mm"
    }

    fun mapCompanyInfo(response: CompanyInfoResponse): CompanyInfoBean {
        return CompanyInfoBean(
            name = response.name,
            founder = response.founder,
            year = response.founded,
            employees = response.employees,
            launchSites = response.launch_sites,
            valuation = response.valuation,
        )
    }

    fun mapRocketLaunches(
        launches: List<LaunchesResponse>, rockets: List<RocketsResponse>,
    ): List<Launch> {
        val timezone = TimeZone.getTimeZone("UTC")
        val dateFormat = SimpleDateFormat(DATE_FORMAT, Locale.getDefault()).apply {
            timeZone = timezone
        }
        val dateTimeFormat = SimpleDateFormat(DATETIME_FORMAT, Locale.getDefault()).apply {
            timeZone = timezone
        }

        val launchList = mutableListOf<Launch>()
        for (launch in launches) {

            val dateMillis = launch.date_utc?.let {
                dateFormat.parse(it)
            }?.time ?: 0L

            val formattedDatetime = dateTimeFormat.format(dateMillis) ?: ""

            val rocketType = rockets.find {
                it.id == launch.rocket
            }?.let { it.name + " / " + it.type } ?: "-"

            val links = launch.links

            val url = links.patch?.small ?: ""
            val article = links.article
            val wikipedia = links.wikipedia
            val webcast = links.webcast

            launchList.add(Launch(
                mission = launch.name,
                dateMillis = dateMillis,
                datetime = formattedDatetime,
                rocketType = rocketType,
                url = url,
                succeeded = launch.success,
                article = article,
                wikipedia = wikipedia,
                webcast = webcast,
            ))
        }
        return launchList
    }
}
