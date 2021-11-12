package com.filippoengidashet.spacex.mvvm.model.source.local

import com.filippoengidashet.spacex.mvvm.model.entity.bean.CompanyInfoBean
import com.filippoengidashet.spacex.mvvm.model.entity.bean.Launch

/**
 * @author Filippo 09/11/2021
 */
interface SpaceXLocal {

    fun getInfo(): CompanyInfoBean?
    fun getLaunches(): List<Launch>
    fun save(infoBean: CompanyInfoBean)
    fun save(launches: List<Launch>)
    fun deleteAll()
}
