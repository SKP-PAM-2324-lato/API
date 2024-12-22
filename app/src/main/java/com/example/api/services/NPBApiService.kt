package com.example.api.services

import com.example.api.response.CurrencyResponse
import retrofit2.Call
import retrofit2.http.GET

interface NPBApiService {
    @GET("exchangerates/tables/A/")
    fun getCurrencyRates(): Call<List<CurrencyResponse>>
}