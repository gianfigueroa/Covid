package com.david.hackro.stats.data.repository

import com.david.hackro.domain.Either
import com.david.hackro.domain.Failure
import com.david.hackro.stats.domain.Provinces
import com.david.hackro.stats.domain.Regions
import com.david.hackro.stats.domain.ReportList
import com.david.hackro.stats.domain.TotalReport

interface StatsRepository {
    suspend fun getRegions(): Either<Failure, Regions>

    suspend fun getTotalReportByDate(date: String): Either<Failure, TotalReport>

    suspend fun getReportsList(
        date: String,
        query: String,
        iso: String,
        regionName: String,
        regionProvidence: String,
        cityName: String
    ) : Either<Failure, ReportList>

    suspend fun getProvinces(iso: String): Either<Failure, Provinces>

}