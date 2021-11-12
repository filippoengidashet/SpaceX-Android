package com.filippoengidashet.spacex.mvvm.model.entity.bean

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.filippoengidashet.spacex.mvvm.model.database.CompanyDataDao

/**
 * @author Filippo 09/11/2021
 */
@Entity(tableName = CompanyDataDao.TABLE_C_INFO)
data class CompanyInfoBean(
    @ColumnInfo(name = "c_name") val name: String,
    @ColumnInfo(name = "c_founder") val founder: String,
    @ColumnInfo(name = "c_year") val year: Int,
    @ColumnInfo(name = "c_employees") val employees: Int,
    @ColumnInfo(name = "c_launch_sites") val launchSites: Int,
    @ColumnInfo(name = "c_valuation") val valuation: Long,
) {

    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}
