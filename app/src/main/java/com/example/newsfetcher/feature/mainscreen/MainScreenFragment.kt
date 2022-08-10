package com.example.newsfetcher.feature.mainscreen

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.example.newsfetcher.R
import com.example.newsfetcher.feature.bookmarks.ui.UiEvent
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainScreenFragment : Fragment(R.layout.fragment_main_screen) {

    private val viewModel: MainScreenViewModel by viewModel()
    private val recyclerView: RecyclerView by lazy { requireActivity().findViewById(R.id.rvArticles) }
    private val adapter: ArticlesAdapter by lazy {
        ArticlesAdapter() { index ->
            viewModel.processUiEvent(com.example.newsfetcher.feature.mainscreen.UiEvent.OnArticleClicked(index))
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.viewState.observe(viewLifecycleOwner, ::render)
        recyclerView.adapter = adapter
    }

    private fun render(viewState: ViewState) {
        adapter.setData(viewState.articles)
    }
}