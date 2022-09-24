package com.example.newsfetcher.feature.mainscreen

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.example.newsfetcher.R
import com.example.newsfetcher.di.BUNDLE_KEY_FOR_ARTICLE_MODEL
import com.example.newsfetcher.feature.detailednews.ui.DetailedNewsFragment
import com.example.newsfetcher.feature.detailednews.ui.DetailedNewsFragment.Companion.newInstance
import com.example.newsfetcher.feature.detailednews.ui.DetailedNewsViewModel
import com.example.newsfetcher.feature.domain.ArticleModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainScreenFragment : Fragment(R.layout.fragment_main_screen) {

    private val viewModel: MainScreenViewModel by viewModel()
    private val recyclerView: RecyclerView by lazy { requireActivity().findViewById(R.id.rvArticles) }
    private val ivSearch: ImageView by lazy { requireActivity().findViewById(R.id.ivSearch) }
    private val tvTitle: TextView by lazy { requireActivity().findViewById(R.id.tvTitle) }
    private val etSearch: EditText by lazy { requireActivity().findViewById(R.id.etSearch) }
    private val adapter: ArticlesAdapter by lazy {
        ArticlesAdapter(
            { currentArticle -> openArticle(currentArticle) },
            { index -> viewModel.processUiEvent(UiEvent.OnArticleClicked(index)) }
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.viewState.observe(viewLifecycleOwner, ::render)
        recyclerView.adapter = adapter

        ivSearch.setOnClickListener {
            viewModel.processUiEvent(com.example.newsfetcher.feature.mainscreen.UiEvent.OnSearchButtonClicked)
        }
        etSearch.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(
                text: CharSequence?,
                start: Int,
                count: Int,
                after: Int
            ) {
            }

            override fun onTextChanged(text: CharSequence?, start: Int, before: Int, count: Int) {
            }

            override fun afterTextChanged(text: Editable?) {
                viewModel.processUiEvent(
                    com.example.newsfetcher.feature.mainscreen.UiEvent.OnSearchEdit(
                        text.toString()
                    )
                )
            }
        })
    }

    private fun render(viewState: ViewState) {
        tvTitle.isVisible = !viewState.isSearchEnabled
        etSearch.isVisible = viewState.isSearchEnabled
        adapter.setData(viewState.articlesShown)
    }

    private fun openArticle(currentArticle: ArticleModel) {
        val bundle = Bundle()
        bundle.putParcelable(BUNDLE_KEY_FOR_ARTICLE_MODEL, currentArticle)
        parentFragmentManager.beginTransaction().add(
            R.id.container, DetailedNewsFragment.newInstance(bundle)
        ).commit()
    }
}