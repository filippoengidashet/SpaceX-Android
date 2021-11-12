package com.filippoengidashet.spacex.mvvm.model.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.filippoengidashet.spacex.mvvm.model.entity.bean.CompanyInfoBean
import com.filippoengidashet.spacex.mvvm.model.entity.bean.Launch

/**
 * @author Filippo 11/11/2021
 */
@Dao
interface CompanyDataDao {

    companion object {
        const val TABLE_C_INFO = "c_info_table"
        const val TABLE_C_LAUNCHES = "launches_table"
    }

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertInfo(info: CompanyInfoBean)

    @Query("SELECT * FROM $TABLE_C_INFO LIMIT 1")
    fun getInfo(): CompanyInfoBean?

    @Query("DELETE FROM $TABLE_C_INFO")
    fun deleteCompanyInfo()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertLaunches(launches: List<Launch>)

    @Query("SELECT * FROM $TABLE_C_LAUNCHES")
    fun getLaunches(): List<Launch>

    @Query("DELETE FROM $TABLE_C_LAUNCHES")
    fun clearLaunches()
}
