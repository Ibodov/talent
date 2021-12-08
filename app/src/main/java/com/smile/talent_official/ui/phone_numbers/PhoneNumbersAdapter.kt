package com.smile.talent_official.ui.phone_numbers

import android.text.Layout
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.smile.talent_official.data.models.PhoneNumber
import com.smile.talent_official.databinding.AdapterPhoneNumberBinding

class PhoneNumbersAdapter(var list: List<PhoneNumber> = emptyList()) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = AdapterPhoneNumberBinding.inflate(inflater, parent, false)
        return PhoneNumberViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as PhoneNumberViewHolder).bind(list.get(position))
    }

    fun submitList(data: List<PhoneNumber>){
        list = data
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return list.size
    }

    private inner class PhoneNumberViewHolder(
        val binding: AdapterPhoneNumberBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: PhoneNumber) {
            binding.item = item
        }
    }
}