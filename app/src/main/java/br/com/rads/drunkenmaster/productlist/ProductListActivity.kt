package rads.com.br.drunkenmaster

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import br.com.rads.drunkenmaster.common.Extras
import br.com.rads.drunkenmaster.geocode.PocAddress
import br.com.rads.drunkenmaster.productlist.ProductListContract
import br.com.rads.drunkenmaster.productlist.ProductListPresenter


class ProductListActivity : AppCompatActivity(), ProductListContract.View {

    private val presenter = ProductListPresenter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val pocAddress = intent.getParcelableExtra(Extras.POC_ADDRESS_EXTRA) as PocAddress
        title = pocAddress.fullAddress
        presenter.loadProductList(pocAddress)

    }

    override fun showProgress() {
    }

    override fun hideProgress() {
    }

    override fun showError() {
    }
}
