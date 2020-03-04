package com.test.mynewsapp.ui.headlines

import android.content.res.Configuration
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.test.mynewsapp.api.Result
import com.test.mynewsapp.databinding.HeadlinesFragmentBinding
import com.test.mynewsapp.di.Injectable
import com.test.mynewsapp.di.factory.injectViewModel
import com.test.mynewsapp.ui.hide
import com.test.mynewsapp.ui.show
import javax.inject.Inject


class HeadLinesFragment : Fragment(), Injectable {
    @Inject lateinit var viewModelFactory: ViewModelProvider.Factory
    private lateinit var viewModel: HeadLinesViewModel

    companion object {
        fun newInstance() = HeadLinesFragment()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        viewModel = injectViewModel(viewModelFactory)
        val binding = HeadlinesFragmentBinding.inflate(inflater, container, false)
        context ?: return binding.root
        val adapter = HeadLinesAdapter()

        if (resources.configuration.orientation == Configuration.ORIENTATION_PORTRAIT) {
            binding.recyclerView.layoutManager = GridLayoutManager(activity, 2)
            (binding.recyclerView.layoutManager as GridLayoutManager).spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
                override fun getSpanSize(position: Int): Int {
                            return when (adapter.getItemViewType(position)) {
                                0 -> 2
                                else -> 1
                    }
                }
            }
        } else {
            binding.recyclerView.layoutManager = GridLayoutManager(activity, 3)
            (binding.recyclerView.layoutManager as GridLayoutManager).spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
                override fun getSpanSize(position: Int): Int {
                    return when (adapter.getItemViewType(position)) {
                        0 -> 3
                        else -> 1
                    }
                }
            }
        }

        binding.recyclerView.adapter = adapter
        subscribeUi(binding, adapter)
        setHasOptionsMenu(true)
        return binding.root
    }

    private fun subscribeUi(binding: HeadlinesFragmentBinding, adapter: HeadLinesAdapter) {

        viewModel.observableHeadLines.observe(viewLifecycleOwner, Observer { result ->
            when (result.status) {
                Result.Status.SUCCESS -> {
                    binding.progressBar.hide()
                    //result.data?.let { adapter.submitList(it) }
                    adapter.submitList(result.data?.articles)
                }
                Result.Status.LOADING -> binding.progressBar.show()
                Result.Status.ERROR -> {
                    binding.progressBar.hide()
                    Snackbar.make(binding.root, result.message!!, Snackbar.LENGTH_LONG).show()
                }
            }
        })
    }
}
