package com.test.mynewsapp.ui.headlinesdetails

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.test.mynewsapp.databinding.DetailsFragmentBinding
import com.test.mynewsapp.di.Injectable
import com.test.mynewsapp.di.factory.injectViewModel
import com.test.mynewsapp.ui.data.model.Article
import javax.inject.Inject

class DetailsFragment : Fragment(), Injectable {
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private lateinit var viewModel: DetailsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel = injectViewModel(viewModelFactory)
        val binding = DetailsFragmentBinding.inflate(inflater, container, false)
        binding.newsArticle = arguments?.getParcelable<Article>("articleTitle")!!

        return binding.root
    }

}
