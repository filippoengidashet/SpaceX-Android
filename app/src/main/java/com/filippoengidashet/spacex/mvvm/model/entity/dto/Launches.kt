package com.filippoengidashet.spacex.mvvm.model.entity.dto

/**
 * @author Filippo 09/11/2021
 */
class Fairings(
    val reused: Boolean,
    val recovery_attempt: Boolean,
    val recovered: Boolean,
    val ships: List<String>?,
)

class Patch(
    val small: String?,
    val large: String?,
)

class Reddit(
    val campaign: String?,
    val launch: String?,
    val media: String?,
    val recovery: String?,
)

class Flickr(
    val small: List<String>?,
    val original: List<String>?,
)

class Links(
    val patch: Patch?,
//    val reddit: Reddit?,
//    val flickr: Flickr?,
//    val presskit: String?,
//    val youtube_id: String?,
    val webcast: String?,
    val article: String?,
    val wikipedia: String?,
)

class LaunchesResponse(
    val links: Links,
    val id: String,
    val name: String,
    val rocket: String,
    val success: Boolean,
    val date_unix: Long,
    val date_utc: String?,
)
