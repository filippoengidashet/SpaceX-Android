package com.filippoengidashet.spacex.mvvm.view.main

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.URLUtil
import androidx.fragment.app.FragmentManager
import androidx.navigation.fragment.findNavController
import com.filippoengidashet.spacex.R
import com.filippoengidashet.spacex.common.UIHelper
import com.filippoengidashet.spacex.mvvm.view.detail.WebFragment
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

/**
 * @author Filippo 09/11/2021
 */
class OpeningOptionsDialog : BottomSheetDialogFragment() {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
//        val dialog = super.onCreateDialog(savedInstanceState)
        return BottomSheetDialog(requireContext(), R.style.SheetDialog)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        return inflater.inflate(R.layout.dialog_fragment_open_with, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val args = requireArguments()
        view.findViewById<View>(R.id.button_open_article).setOnClickListener {
            openLink(args.getString(ARGS_LINK_ARTICLE))
        }
        view.findViewById<View>(R.id.button_open_wikipedia).setOnClickListener {
            openLink(args.getString(ARGS_LINK_WIKI))
        }
        view.findViewById<View>(R.id.button_open_video).setOnClickListener {
            openLink(args.getString(ARGS_LINK_VIDEO))
        }
    }

    private fun openLink(link: String?) {
        if (URLUtil.isValidUrl(link)) {
            findNavController().navigate(
                R.id.action_fragmentMain_to_fragmentWeb,
                Bundle().apply {
                    putString(WebFragment.ARGS_WEB_URL, link)
                }
            )
//            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(link))
//            startActivity(intent)
            dismiss()
        } else {
            UIHelper.showToast(requireContext(), getString(R.string.text_error_invalid_link))
        }
    }

    companion object {

        private const val TAG = "tag::open-with-dialog"
        private const val ARGS_LINK_ARTICLE = "args.link.article"
        private const val ARGS_LINK_WIKI = "args.link.wiki"
        private const val ARGS_LINK_VIDEO = "args.link.video"

        fun show(
            fm: FragmentManager, article: String?, wikipedia: String?, webcast: String?,
        ) {
            OpeningOptionsDialog().apply {
                arguments = Bundle().apply {
                    putString(ARGS_LINK_ARTICLE, article)
                    putString(ARGS_LINK_WIKI, wikipedia)
                    putString(ARGS_LINK_VIDEO, webcast)
                }
            }.show(fm, TAG)
        }
    }
}
