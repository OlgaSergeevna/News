package com.sylko.news

import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment
import androidx.recyclerview.widget.RecyclerView
import com.sylko.news.adapters.NewsAdapter
import com.sylko.news.databinding.FragmentNewsBinding
import com.sylko.news.pojo.News


class NewsFragment : Fragment(R.layout.fragment_news), NewsAdapter.OnItemClickListener {

    private lateinit var recyclerView: RecyclerView
    private lateinit var binding: FragmentNewsBinding
    private var adapter: NewsAdapter? = null
    private lateinit var viewModel: NewsViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentNewsBinding.bind(view)

        recyclerView = binding.rvNews

        observeLiveData()
        initScrollRefresh()
    }

    private fun initScrollRefresh(){
        binding.srLayout.setOnRefreshListener {
            viewModel.update()
            adapter?.notifyDataSetChanged()
            binding.srLayout.isRefreshing = false
        }

        binding.srLayout.setColorSchemeResources(
            android.R.color.holo_blue_bright
        )
    }

    private fun observeLiveData(){
        viewModel = ViewModelProvider(this).get(NewsViewModel::class.java)

        viewModel.allNews.observe(viewLifecycleOwner, {
            initAdapter(it)
        })

    }

    private fun initAdapter(data: List<News>) {
        adapter = NewsAdapter(data, this)
        recyclerView.adapter = adapter
    }

    override fun onItemClick(position: Int) {
        val uid = adapter?.getId(position)
        val bundle = bundleOf(
            Pair("KEY_UID", uid),
        )
        NavHostFragment.findNavController(this).navigate(
            R.id.action_newsFragment_to_detailFragment,
            bundle
        )
    }
}