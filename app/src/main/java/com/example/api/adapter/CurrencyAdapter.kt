package com.example.api.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.api.R
import com.example.api.response.CurrencyRate

class CurrencyAdapter(private val rates: List<CurrencyRate>): RecyclerView.Adapter<CurrencyAdapter.CurrencyViewHolder>() {

    class CurrencyViewHolder(view: View): RecyclerView.ViewHolder(view){
        val currencyName =  view.findViewById<TextView>(R.id.currencyName)
        val currencyCode =  view.findViewById<TextView>(R.id.currencyCode)
        val currencyRate =  view.findViewById<TextView>(R.id.currencyRate)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CurrencyViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.currency_item, parent, false)
        return CurrencyViewHolder(view)
    }

    override fun getItemCount(): Int {
        return rates.size
    }

    override fun onBindViewHolder(holder: CurrencyViewHolder, position: Int) {
        val rate = rates[position]
        holder.currencyName.text = rate.currency
        holder.currencyCode.text = rate.code
        holder.currencyRate.text = rate.mid.toString()
    }


}