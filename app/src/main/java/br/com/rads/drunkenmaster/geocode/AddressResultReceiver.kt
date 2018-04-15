package br.com.rads.drunkenmaster.geocode

import android.os.Bundle
import android.os.Handler
import android.os.ResultReceiver

class AddressResultReceiver(handler: Handler) : ResultReceiver(handler) {

    override fun onReceiveResult(resultCode: Int, resultData: Bundle?) {
        super.onReceiveResult(resultCode, resultData)
    }

}