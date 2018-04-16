package br.com.rads.drunkenmaster.common

import android.view.View

//Views
fun View.visible() {
    this.visibility = View.VISIBLE
}

fun View.invisible() {
    this.visibility = View.INVISIBLE
}

//Float
fun Float.toMoney(): String {
    return if (this <= 0.0) {
        "$ - "
    } else {
        "$ " + this.toString().replace(".", ",")
    }
}