package com.filippoengidashet.spacex.mvvm.view.detail

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.webkit.WebView
import android.webkit.WebViewClient
import com.filippoengidashet.spacex.R
import com.filippoengidashet.spacex.common.BaseFragment

/**
 * @author Filippo 10/11/2021
 */
class WebFragment : BaseFragment(R.layout.fragment_web) {

    @SuppressLint("SetJavaScriptEnabled")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val webView = view.findViewById<WebView>(R.id.web_view).apply {
            settings.javaScriptEnabled = true
            webViewClient = WebViewClient()
        }
        arguments?.getString(ARGS_WEB_URL)?.also { url ->
            webView.loadUrl(url)
        }
    }

    companion object {

        const val ARGS_WEB_URL = "args.web.url"
    }
}
