package com.pbworkshop.snackbarlibrary.callback

import com.google.android.material.snackbar.Snackbar

interface TimeoutDismissCallback {
    fun onTimedOut(snackbar: Snackbar?)
}