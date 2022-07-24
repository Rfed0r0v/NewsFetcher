package com.example.newsfetcher.feature.di

import com.example.newsfetcher.feature.data.NewsApi
import org.koin.dsl.module
import retrofit2.Retrofit

val mainScreenModule = module {
    single {
        get<Retrofit>().create(NewsApi::class.java)
    }
}