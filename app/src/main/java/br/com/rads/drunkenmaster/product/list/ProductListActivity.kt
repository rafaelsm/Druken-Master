package rads.com.br.drunkenmaster

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.GridLayoutManager
import br.com.rads.drunkenmaster.common.Extras
import br.com.rads.drunkenmaster.geocode.PocAddress
import br.com.rads.drunkenmaster.product.Product
import br.com.rads.drunkenmaster.product.list.ProductListAdapter
import br.com.rads.drunkenmaster.product.list.ProductListContract
import br.com.rads.drunkenmaster.product.list.ProductListPresenter
import kotlinx.android.synthetic.main.activity_product_list.*


class ProductListActivity : AppCompatActivity(), ProductListContract.View {

    private val presenter = ProductListPresenter(this)
    private val productAdapter = ProductListAdapter(mutableListOf())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product_list)
        val pocAddress = intent.getParcelableExtra(Extras.POC_ADDRESS_EXTRA) as PocAddress
        title = pocAddress.fullAddress
        presenter.loadProductList(pocAddress)

        recyclerView2.apply {
            setHasFixedSize(false)
            layoutManager = GridLayoutManager(this@ProductListActivity, 2)
            adapter = productAdapter
        }
    }

    override fun showProgress() {
    }

    override fun hideProgress() {
    }

    override fun showError() {
    }

    override fun showProductList(productList: List<Product>) {
        runOnUiThread {
            productAdapter.addAll(productList)
        }
    }
}
