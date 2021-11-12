package com.filippoengidashet.spacex.common

import android.content.Context
import android.widget.Toast

/**
 * @author Filippo 10/11/2021
 */
object UIHelper {

    fun showToast(context: Context, message: String?, length: Int = Toast.LENGTH_SHORT) {
        Toast.makeText(context, message, length).show()
    }
}
