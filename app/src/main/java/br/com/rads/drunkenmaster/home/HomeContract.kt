package br.com.rads.drunkenmaster.home

import android.content.Context
import br.com.rads.drunkenmaster.geocode.PocAddress

interface HomeContract {

    interface View {
        fun context(): Context
        fun showLoading()
        fun hideLoading()
        fun addressFound(addresses: List<PocAddress>)
        fun addressNotFound()
    }

    interface Presenter {
        fun searchAddress(address: String)
    }

}