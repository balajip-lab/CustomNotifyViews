package com.pbworkshop.snackbarlibrary.callback

import com.google.android.material.snackbar.Snackbar




interface DismissCallback {
    fun onDismissed(snackbar: Snackbar?, dismissEvent: Int)
}