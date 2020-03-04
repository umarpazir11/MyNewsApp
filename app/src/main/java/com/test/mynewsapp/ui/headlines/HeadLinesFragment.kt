package com.test.mynewsapp.ui.headlines

import android.content.res.Configuration
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.observe
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.test.mynewsapp.R
import com.test.mynewsapp.databinding.HeadlinesFragmentBinding
import com.test.mynewsapp.di.Injectable
import com.test.mynewsapp.di.factory.injectViewModel
import com.test.mynewsapp.ui.headlinesdetails.DetailsFragment
import com.test.mynewsapp.ui.hide
import com.test.mynewsapp.util.ConnectivityUtil
import javax.inject.Inject

class HeadLinesFragment : Fragment(), Injectable {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private lateinit var viewModel: HeadLinesViewModel

    companion object {
        const val SPAN_COUNT_TWO = 2
        const val SPAN_COUNT_THREE = 3
    }

    private var connectivityAvailable: Boolean = false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel = injectViewModel(viewModelFactory)

        connectivityAvailable = ConnectivityUtil.isConnected(context!!)

        val binding = HeadlinesFragmentBinding.inflate(inflater, container, false)
        context ?: return binding.root
        val adapter = HeadLinesAdapter()

        if (resources.configuration.orientation == Configuration.ORIENTATION_PORTRAIT) {
            binding.recyclerView.layoutManager = GridLayoutManager(activity, SPAN_COUNT_TWO)
            (binding.recyclerView.layoutManager as GridLayoutManager).spanSizeLookup =
                object : GridLayoutManager.SpanSizeLookup() {
                    override fun getSpanSize(position: Int): Int {
                        return when (adapter.getItemViewType(position)) {
                            0 -> 2
                            else -> 1
                        }
                    }
                }
        } else {
            binding.recyclerView.layoutManager = GridLayoutManager(activity, SPAN_COUNT_THREE)
            (binding.recyclerView.layoutManager as GridLayoutManager).spanSizeLookup =
                object : GridLayoutManager.SpanSizeLookup() {
                    override fun getSpanSize(position: Int): Int {
                        return when (adapter.getItemViewType(position)) {
                            0 -> 3
                            else -> 1
                        }
                    }
                }
        }

        binding.recyclerView.adapter = adapter
        if (connectivityAvailable)
            subscribeUi(binding, adapter)
        else {
            Snackbar.make(binding.root, getString(R.string.no_internet), Snackbar.LENGTH_LONG)
                .show()
            binding.progressBar.hide()
        }
        return binding.root
    }

    private fun subscribeUi(binding: HeadlinesFragmentBinding, adapter: HeadLinesAdapter) {
        viewModel.legoSets.observe(viewLifecycleOwner) {
            binding.progressBar.hide()
            adapter.submitList(it)
        }
    }
}
