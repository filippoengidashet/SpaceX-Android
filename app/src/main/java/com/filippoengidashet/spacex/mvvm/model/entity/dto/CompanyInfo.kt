package com.filippoengidashet.spacex.mvvm.model.entity.dto

/**
 * @author Filippo 09/11/2021
 */
class LinksResponse(
    val address: String,
    val city: String,
    val state: String,
)

class HeadQuartersResponse(
    val website: String,
    val flickr: String,
    val twitter: String,
    val elon_twitter: String,
)

class CompanyInfoResponse(
    val headquarters: HeadQuartersResponse,
    val links: LinksResponse,
    val name: String,
    val founder: String,
    val founded: Int,
    val employees: Int,
    val vehicles: Int,
    val launch_sites: Int,
    val test_sites: Int,
    val ceo: String,
    val cto: String,
    val coo: String,
    val cto_propulsion: String,
    val valuation: Long,
    val summary: String,
    val id: String?,
)
