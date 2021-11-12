package com.filippoengidashet.spacex.mvvm.model.entity.bean

import android.text.TextUtils

/**
 * @author Filippo 11/11/2021
 */
class FilterOption(
    val year: Int, val launchSuccess: Boolean, val sortOrder: SortOrder,
)

enum class SortOrder(val order: String) {

    ASC("ASC"),
    DESC("DESC");

    companion object {

        fun from(order: String): SortOrder {
            var sortOrder = ASC
            val values = values()
            for (value in values) {
                if (TextUtils.equals(order, value.order)) {
                    sortOrder = value
                    break
                }
            }
            return sortOrder
        }
    }
}
