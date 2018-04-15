package br.com.rads.drunkenmaster.home

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import br.com.rads.drunkenmaster.geocode.PocAddress
import rads.com.br.drunkenmaster.R


class AddressesAdapter(private val addresses: MutableList<PocAddress>)
    : RecyclerView.Adapter<AddressesAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val textView = LayoutInflater.from(parent.context)
                .inflate(R.layout.cell_address, parent, false)
                as TextView
        return ViewHolder(textView)
    }

    override fun getItemCount() = addresses.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.addressTextView.text = addresses[position].fullAddress
    }

    class ViewHolder(val addressTextView: TextView) : RecyclerView.ViewHolder(addressTextView)

    fun addAll(newAddresses: List<PocAddress>) {
        addresses.clear()
        addresses.addAll(newAddresses)
        notifyDataSetChanged()
    }
}