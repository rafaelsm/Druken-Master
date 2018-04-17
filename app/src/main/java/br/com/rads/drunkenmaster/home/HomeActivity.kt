package br.com.rads.drunkenmaster.home

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.widget.LinearLayout
import android.widget.Toast
import br.com.rads.drunkenmaster.common.Extras
import br.com.rads.drunkenmaster.geocode.PocAddress
import br.com.rads.drunkenmaster.common.invisible
import br.com.rads.drunkenmaster.common.visible
import com.jakewharton.rxbinding2.widget.RxTextView
import io.reactivex.android.schedulers.AndroidSchedulers
import kotlinx.android.synthetic.main.activity_delivery_address.*
import br.com.rads.drunkenmaster.product.list.ProductListActivity
import rads.com.br.drunkenmaster.R
import java.util.concurrent.TimeUnit

class HomeActivity : AppCompatActivity(), HomeContract.View {

    private val presenter = HomePresenter(this)
    private val addressesAdapter = AddressesAdapter(mutableListOf(), presenter)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_delivery_address)

        recyclerView.apply {
            setHasFixedSize(false)
            layoutManager = LinearLayoutManager(this@HomeActivity)
            adapter = addressesAdapter
            addItemDecoration(DividerItemDecoration(this@HomeActivity, LinearLayout.VERTICAL))
        }

        RxTextView.textChangeEvents(editText)
                .debounce(400, TimeUnit.MILLISECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe {
                    presenter.searchAddress(it.text().toString())
                }
    }

    override fun context() = this

    override fun showLoading() = progressBar2.visible()

    override fun hideLoading() = progressBar2.invisible()

    override fun addressNotFound() {
        Toast.makeText(this, "Address not found", Toast.LENGTH_LONG).show()
    }

    override fun addressFound(addresses: List<PocAddress>) {
        addressesAdapter.addAll(addresses)
    }

    override fun startProductListActivity(pocAddress: PocAddress) {
        startActivity(Intent(this@HomeActivity, ProductListActivity::class.java)
                .putExtra(Extras.POC_ADDRESS_EXTRA, pocAddress))
    }
}
