package com.filippoengidashet.spacex.mvvm.model

/**
 * @author Filippo 09/11/2021
 */
object AppConstants {

    object Database {

        const val DB_NAME = "spacex-data.db"
        const val DB_VERSION = 0x001
    }

    object Api {

        const val BASE_URL = "https://api.spacexdata.com"
        const val ENDPOINT_COMPANY = "/v4/company"
        const val ENDPOINT_LAUNCHES = "/v4/launches"
        const val ENDPOINT_ROCKETS = "/v4/rockets"
    }
}
