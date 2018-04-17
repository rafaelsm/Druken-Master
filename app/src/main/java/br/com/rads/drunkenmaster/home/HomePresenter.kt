package br.com.rads.drunkenmaster.home

import android.os.Bundle
import android.os.Handler
import android.os.ResultReceiver
import br.com.rads.drunkenmaster.geocode.FetchAddressIntentService
import br.com.rads.drunkenmaster.geocode.PocAddress

class HomePresenter(val view: HomeContract.View)
    : HomeContract.Presenter, AddressesAdapter.AddressSelectedListener {

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
        FetchAddressIntentService.startActionSearchAddress(view.context(), address, receiver)
    }

    //region Address Selected Listener
    override fun addressSelected(pocAddress: PocAddress) {
        view.startProductListActivity(pocAddress)
    }
    //endregion

}