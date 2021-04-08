package com.pbworkshop.snackbarlibrary.callback

import com.google.android.material.snackbar.Snackbar

interface ActionDismissCallback {
    fun onActionPressed(snackbar: Snackbar)
}