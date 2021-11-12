package com.filippoengidashet.spacex.di

import android.content.Context
import androidx.room.Room
import com.filippoengidashet.spacex.mvvm.model.AppConstants
import com.filippoengidashet.spacex.mvvm.model.database.AppDatabase
import com.filippoengidashet.spacex.mvvm.model.database.CompanyDataDao
import com.filippoengidashet.spacex.mvvm.model.source.local.SpaceXLocal
import com.filippoengidashet.spacex.mvvm.model.source.local.SpaceXLocalImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import java.util.*
import javax.inject.Singleton

/**
 * @author Filippo 09/11/2021
 */
@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Singleton
    @Provides
    fun provideContext(@ApplicationContext context: Context) = context

    @Provides
    fun provideCalendar(): Calendar {
        return Calendar.getInstance(TimeZone.getTimeZone("UTC"))
    }

    @Provides
    fun bindSpaceXLocal(impl: SpaceXLocalImpl): SpaceXLocal = impl

    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext context: Context): AppDatabase {
        return Room.databaseBuilder(context, AppDatabase::class.java, AppConstants.Database.DB_NAME)
            .build()
    }

    @Provides
    @Singleton
    fun provideCompanyDataDao(database: AppDatabase): CompanyDataDao {
        return database.companyDataDao()
    }
}
