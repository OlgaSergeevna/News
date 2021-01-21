package com.sylko.news

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.core.os.bundleOf
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment
import com.squareup.picasso.Picasso
import com.sylko.news.databinding.DetailNewsBinding
import com.sylko.news.utils.convertPublishedAtToDate

class DetailFragment : Fragment(R.layout.detail_news) {

    private lateinit var binding: DetailNewsBinding
    private lateinit var viewModel: NewsViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = DetailNewsBinding.bind(view)

        val id = arguments?.get("KEY_UID") as String

        observeLiveData(id)

        binding.tvMore.setOnClickListener {
            val bundle = bundleOf(
                Pair("KEY_UID", id),
            )
            NavHostFragment.findNavController(this).navigate(R.id.action_detailFragment_to_webFragment, bundle)
        }
    }

    private fun observeLiveData(id: String){
        viewModel = ViewModelProvider(this).get(NewsViewModel::class.java)

        viewModel.getDetailNews(id).observe(viewLifecycleOwner, {
            binding.tvTitleDetail.text = it.title
            binding.tvDescriptionDetail.text = it.description
            Picasso.get().load(it.urlToImage).into(binding.ivDetail)
            binding.tvDate.text = convertPublishedAtToDate(it.publishedAt.toString())
        })
    }
}