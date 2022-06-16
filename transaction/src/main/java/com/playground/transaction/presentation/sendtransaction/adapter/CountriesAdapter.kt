package com.playground.transaction.presentation.sendtransaction.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.playground.domain.model.Country
import com.playground.transaction.databinding.ItemCountryBinding

internal class CountriesAdapter(
    private val onItemClick: (Country) -> Unit = {}
) : RecyclerView.Adapter<CountriesAdapter.CountryViewHolder>() {

    private val countries = Country.getAllCountries()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountryViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemCountryBinding.inflate(inflater, parent, false)
        return CountryViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CountryViewHolder, position: Int) {
        val model = countries[position]
        holder.bind(model)
    }

    override fun getItemCount(): Int = countries.size

    inner class CountryViewHolder(
        private val binding: ItemCountryBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(country: Country) {
            binding.imageViewFlag.setImageResource(country.icon)
            binding.textViewCountry.setText(country.countryName)
            binding.root.setOnClickListener { onItemClick(country) }
        }
    }
}
