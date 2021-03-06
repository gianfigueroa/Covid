package com.david.hackro.covid.di

import com.david.hackro.covid.presentation.viewmodel.CountryDetailViewModel
import com.david.hackro.covid.presentation.viewmodel.DailyReportAllCountriesViewModel
import com.david.hackro.covid.presentation.viewmodel.MapViewModel
import com.david.hackro.covid.presentation.viewmodel.TotalReportViewModel
import com.david.hackro.stats.di.dataSourceModule
import com.david.hackro.stats.di.networkHandlerModule
import com.david.hackro.stats.di.networkModule
import com.david.hackro.stats.di.repositoryModule
import com.david.hackro.stats.di.useCaseModule
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.loadKoinModules
import org.koin.core.module.Module
import org.koin.dsl.module

fun injectFeatures() = loadFeature

private val loadFeature by lazy {
    loadKoinModules(
        arrayListOf(
            viewModelModule,
            useCaseModule,
            repositoryModule,
            dataSourceModule,
            networkModule,
            networkHandlerModule
        )
    )
}

val viewModelModule: Module = module {
    viewModel {
        TotalReportViewModel(getLatestTotalsUseCase = get())
    }

    viewModel {
        DailyReportAllCountriesViewModel(getDailyReportAllCountriesUseCase = get())
    }

    viewModel {
        MapViewModel(getDailyReportAllCountriesUseCase = get())
    }

    viewModel {
        CountryDetailViewModel(getLatestCountryDataByCodeUseCase = get())
    }
}
