package com.sylko.news

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.sylko.news.databinding.WebFragmentBinding

class WebFragment: Fragment(R.layout.web_fragment) {

    private lateinit var binding: WebFragmentBinding

    @SuppressLint("SetJavaScriptEnabled")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = WebFragmentBinding.bind(view)

        val id = arguments?.get("KEY_UID") as String

        binding.webView.settings.javaScriptEnabled = true
        binding.webView.loadUrl(id)
    }
}