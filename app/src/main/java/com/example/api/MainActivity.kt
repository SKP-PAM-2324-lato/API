package com.example.api

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.api.adapter.CurrencyAdapter
import com.example.api.response.CurrencyResponse
import com.example.api.services.NPBApiService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerView = findViewById<RecyclerView>(R.id.main)
        recyclerView.layoutManager = LinearLayoutManager(this)

        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.nbp.pl/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val apiService = retrofit.create(NPBApiService::class.java)

        apiService.getCurrencyRates().enqueue(object : Callback<List<CurrencyResponse>>{
            override fun onResponse(
                call: Call<List<CurrencyResponse>>,
                response: Response<List<CurrencyResponse>>
            ) {
                if(response.isSuccessful){
                    val rates = response.body()?.firstOrNull()?.rates?: emptyList()
                    recyclerView.adapter = CurrencyAdapter(rates)
                }else{
                    Toast.makeText(this@MainActivity, "Error ${response.message()}", Toast.LENGTH_LONG).show()
                }
            }

            override fun onFailure(p0: Call<List<CurrencyResponse>>, p1: Throwable) {
                Toast.makeText(this@MainActivity, "Error ${p1.message}", Toast.LENGTH_LONG).show()

            }

        })
    }
}