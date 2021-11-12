package com.filippoengidashet.spacex.common

import android.widget.Toast
import androidx.fragment.app.Fragment

/**
 * @author Filippo 10/11/2021
 */
abstract class BaseFragment : Fragment {

    constructor() : super()

    constructor(layoutId: Int) : super(layoutId)

    protected fun showToast(message: String?, length: Int = Toast.LENGTH_SHORT) {
        UIHelper.showToast(requireContext(), message, length)
    }
}
