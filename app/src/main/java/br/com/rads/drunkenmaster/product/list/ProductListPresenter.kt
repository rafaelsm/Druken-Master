package br.com.rads.drunkenmaster.product.list

import android.util.Log
import br.com.rads.drunkenmaster.PocSearchMethodQuery
import br.com.rads.drunkenmaster.geocode.PocAddress
import br.com.rads.drunkenmaster.graphql.GraphqlProvider
import br.com.rads.drunkenmaster.product.Product
import com.apollographql.apollo.ApolloCall
import com.apollographql.apollo.api.Response
import com.apollographql.apollo.exception.ApolloException
import java.util.*

class ProductListPresenter(private val view: ProductListContract.View)
    : ProductListContract.Presenter {

    override fun loadProductList(pocAddress: PocAddress) {
        val searchMethodQuery = PocSearchMethodQuery.builder()
                .lat(pocAddress.lat.toString())
                .lng(pocAddress.lng.toString())
                .now(Date())
                .algorithm("NEAREST")
                .build()

        val queryCall = GraphqlProvider.provideGraphql().query(searchMethodQuery)
        queryCall.enqueue(object : ApolloCall.Callback<PocSearchMethodQuery.Data>() {
            override fun onFailure(e: ApolloException) {
                Log.e("APOLLO_CALL", "deu ruim $e")
            }

            override fun onResponse(response: Response<PocSearchMethodQuery.Data>) {
                Log.d("APOLLO_CALL", "deu bom")
                val data = response.data()
                Log.d("APOLLO_CALL", "$data")

                view.showProductList(mock())
            }
        })
    }

    private fun mock(): List<Product> {
        return listOf(
                Product("name", "description", "url", 1.1f),
                Product("name", "description", "url", 1.1f),
                Product("name", "description", "url", 1.1f),
                Product("name", "description", "url", 1.1f),
                Product("name", "description", "url", 1.1f),
                Product("name", "description", "url", 1.1f),
                Product("name", "description", "url", 1.1f),
                Product("name", "description", "url", 1.1f),
                Product("name", "description", "url", 1.1f),
                Product("name", "description", "url", 1.1f),
                Product("name", "description", "url", 1.1f),
                Product("name", "description", "url", 1.1f),
                Product("name", "description", "url", 1.1f),
                Product("name", "description", "url", 1.1f),
                Product("name", "description", "url", 1.1f)
        )
    }

    override fun filterProductList(pocIds: List<String>, category: String?, productName: String?) {

    }
}