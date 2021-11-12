package com.filippoengidashet.spacex.mvvm.model.entity.bean

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.filippoengidashet.spacex.mvvm.model.database.CompanyDataDao

/**
 * @author Filippo 09/11/2021
 */
@Entity(tableName = CompanyDataDao.TABLE_C_LAUNCHES)
data class Launch(
    @ColumnInfo(name = "launch_mission") val mission: String?,
    @ColumnInfo(name = "launch_date_millis") val dateMillis: Long,
    @ColumnInfo(name = "launch_datetime") val datetime: String,
    @ColumnInfo(name = "launch_rocket_type") val rocketType: String,
    @ColumnInfo(name = "launch_img_url") val url: String,
    @ColumnInfo(name = "launch_success") val succeeded: Boolean,
    @ColumnInfo(name = "launch_article_link") val article: String?,
    @ColumnInfo(name = "launch_wiki_link") val wikipedia: String?,
    @ColumnInfo(name = "launch_video_link") val webcast: String?,
) {

    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}
