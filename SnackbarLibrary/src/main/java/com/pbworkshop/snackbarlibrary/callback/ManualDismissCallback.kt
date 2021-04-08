package com.pbworkshop.snackbarlibrary.callback

import com.google.android.material.snackbar.Snackbar


interface ManualDismissCallback {
    fun onManuallyDismissed(snackbar: Snackbar?)
}