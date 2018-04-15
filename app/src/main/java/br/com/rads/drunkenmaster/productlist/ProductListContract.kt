package br.com.rads.drunkenmaster.productlist

import br.com.rads.drunkenmaster.geocode.PocAddress

interface ProductListContract {

    interface View {
        fun showProgress()
        fun hideProgress()
        fun showError()
    }

    interface Presenter {
        fun loadProductList(pocAddress: PocAddress)
        fun filterProductList(pocIds: List<String>, category: String?, productName: String?)
    }

}