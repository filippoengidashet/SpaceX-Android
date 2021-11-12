package com.filippoengidashet.spacex.mvvm.model.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.filippoengidashet.spacex.mvvm.model.AppConstants
import com.filippoengidashet.spacex.mvvm.model.entity.bean.CompanyInfoBean
import com.filippoengidashet.spacex.mvvm.model.entity.bean.Launch

/**
 * @author Filippo 11/11/2021
 */
@Database(
    entities = [CompanyInfoBean::class, Launch::class], version = AppConstants.Database.DB_VERSION
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun companyDataDao(): CompanyDataDao
}
