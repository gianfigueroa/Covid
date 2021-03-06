package com.david.hackro.stats.di

import com.david.hackro.androidext.NetworkHandler
import com.david.hackro.network.createNetworkClient
import com.david.hackro.stats.BuildConfig
import com.david.hackro.stats.BuildConfig.BASE_URL
import com.david.hackro.stats.data.datasource.remote.StatsApi
import com.david.hackro.stats.data.datasource.remote.StatsRemoteDataSource
import com.david.hackro.stats.data.repository.StatsRepository
import com.david.hackro.stats.data.repository.StatsRepositoryImpl
import com.david.hackro.stats.domain.usecase.GetDailyReportAllCountriesUseCase
import com.david.hackro.stats.domain.usecase.GetLatestCountryDataByNameUseCase
import com.david.hackro.stats.domain.usecase.GetLatestTotalsUseCase
import org.koin.core.module.Module
import org.koin.dsl.module
import retrofit2.Retrofit

val useCaseModule: Module = module {
    factory { GetDailyReportAllCountriesUseCase(repository = get()) }
    factory { GetLatestCountryDataByNameUseCase(repository = get()) }
    factory { GetLatestTotalsUseCase(repository = get()) }
}

val repositoryModule: Module = module {
    single { StatsRepositoryImpl(networkHandler = get(), remoteDataSource = get()) as StatsRepository }
}

val dataSourceModule: Module = module {
    single { StatsRemoteDataSource(statsApi = get()) }
}

val networkModule: Module = module {
    single { get<Retrofit>().create(StatsApi::class.java) }
    single { createNetworkClient(baseUrl = BASE_URL, debug = BuildConfig.DEBUG, context = get()) }
}

val networkHandlerModule: Module = module {
    single { NetworkHandler(context = get()) }
}
