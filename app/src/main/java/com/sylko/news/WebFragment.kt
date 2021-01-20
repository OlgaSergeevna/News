package com.sylko.news

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.sylko.news.databinding.WebFragmentBinding

class WebFragment: Fragment(R.layout.web_fragment) {

    private lateinit var binding: WebFragmentBinding
    private lateinit var viewModel: NewsViewModel

    @SuppressLint("SetJavaScriptEnabled")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = WebFragmentBinding.bind(view)

        val id = arguments?.get("KEY_UID") as String

        viewModel = ViewModelProvider(this).get(NewsViewModel::class.java)

        viewModel.getDetailNews(id).observe(viewLifecycleOwner, {
            binding.webView.settings.javaScriptEnabled = true
            binding.webView.loadUrl(it.url)
        })

    }
}