package rads.com.br.drunkenmaster

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import br.com.rads.drunkenmaster.PocSearchMethodQuery
import br.com.rads.drunkenmaster.type.CustomType
import com.apollographql.apollo.ApolloCall
import com.apollographql.apollo.ApolloClient
import com.apollographql.apollo.api.Response
import com.apollographql.apollo.exception.ApolloException
import okhttp3.OkHttpClient
import java.util.*
import com.apollographql.apollo.CustomTypeAdapter
import java.text.ParseException
import java.text.SimpleDateFormat


class MainActivity : AppCompatActivity() {

    private val BASE_URL = "https://803votn6w7.execute-api.us-west-2.amazonaws.com/dev/public/graphql"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val okHttpClient = OkHttpClient.Builder()
                .build()

        val dateTimeAdapter = object : CustomTypeAdapter<Date> {
            val ISO8601_DATE_FORMAT = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ")
            override fun decode(value: String): Date {
                try {
                    return ISO8601_DATE_FORMAT.parse(value)
                } catch (e: ParseException) {
                    throw RuntimeException(e)
                }
            }

            override fun encode(value: Date): String {
                return ISO8601_DATE_FORMAT.format(value)
            }
        }

        val timeAdapter = object : CustomTypeAdapter<Date> {
            val ISO8601_DATE_FORMAT = SimpleDateFormat("HH:mm:ss")
            override fun decode(value: String): Date {
                try {
                    return ISO8601_DATE_FORMAT.parse(value)
                } catch (e: ParseException) {
                    throw RuntimeException(e)
                }
            }

            override fun encode(value: Date): String {
                return ISO8601_DATE_FORMAT.format(value)
            }
        }

        val apolloClient = ApolloClient.builder()
                .serverUrl(BASE_URL)
                .okHttpClient(okHttpClient)
                .addCustomTypeAdapter(CustomType.DATETIME, dateTimeAdapter)
                .addCustomTypeAdapter(CustomType.TIME, timeAdapter)
                .build()

        val searchMethodQuery = PocSearchMethodQuery.builder()
                .lat("-23.632919")
                .lng("-46.699453")
                .now(Date())
                .algorithm("NEAREST")
                .build()


        val queryCall = apolloClient.query(searchMethodQuery)
        queryCall.enqueue(object : ApolloCall.Callback<PocSearchMethodQuery.Data>() {
            override fun onFailure(e: ApolloException) {
                Log.e("APOLLO_CALL", "deu ruim $e")
            }

            override fun onResponse(response: Response<PocSearchMethodQuery.Data>) {
                Log.d("APOLLO_CALL", "deu bom")
                val data = response.data()
                Log.d("APOLLO_CALL", "$data")
            }
        })

    }
}
