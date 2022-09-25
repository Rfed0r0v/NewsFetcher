package com.example.newsfetcher.feature.detailednews.di

import com.example.newsfetcher.feature.detailednews.ui.DetailedNewsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val infoScreenModule = module {
    viewModel { DetailedNewsViewModel() }
}