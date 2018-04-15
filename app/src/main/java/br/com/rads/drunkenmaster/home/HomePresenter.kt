package br.com.rads.drunkenmaster.home

import android.content.Context
import android.location.Geocoder
import android.os.Bundle
import android.os.Handler
import android.os.ResultReceiver
import br.com.rads.drunkenmaster.geocode.FetchAddressIntentService
import br.com.rads.drunkenmaster.geocode.PocAddress


class HomePresenter(val view: HomeContract.View)
    : HomeContract.Presenter {

    private val receiver = object : ResultReceiver(Handler()) {
        override fun onReceiveResult(resultCode: Int, resultData: Bundle?) {
            super.onReceiveResult(resultCode, resultData)
            val addresses = resultData?.getParcelableArray("addresses")
            if (addresses?.isNotEmpty() == true) {
                view.addressFound(addresses.map { it as PocAddress })
            } else {
                view.addressNotFound()
            }

            view.hideLoading()
        }
    }

    override fun searchAddress(address: String) {
        view.showLoading()
//        FetchAddressIntentService.startActionStop(view.context())
        FetchAddressIntentService.startActionSearchAddress(view.context(), address, receiver)
    }

}