package com.filippoengidashet.spacex.mvvm.model.source.local

import com.filippoengidashet.spacex.mvvm.model.database.CompanyDataDao
import com.filippoengidashet.spacex.mvvm.model.entity.bean.CompanyInfoBean
import com.filippoengidashet.spacex.mvvm.model.entity.bean.Launch
import javax.inject.Inject

/**
 * @author Filippo 09/11/2021
 */
class SpaceXLocalImpl @Inject constructor(
    private val dao: CompanyDataDao
): SpaceXLocal {

    override fun getInfo(): CompanyInfoBean? = dao.getInfo()

    override fun getLaunches(): List<Launch> = dao.getLaunches()

    override fun save(infoBean: CompanyInfoBean) {
        dao.insertInfo(infoBean)
    }

    override fun save(launches: List<Launch>) {
        dao.insertLaunches(launches)
    }

    override fun deleteAll() {
        dao.deleteCompanyInfo()
        dao.clearLaunches()
    }
}
