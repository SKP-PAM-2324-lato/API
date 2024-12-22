package com.example.api.response

data class CurrencyResponse(
    val table: String,
    val no: String,
    val effectiveDate: String,
    val rates: List<CurrencyRate>
)
