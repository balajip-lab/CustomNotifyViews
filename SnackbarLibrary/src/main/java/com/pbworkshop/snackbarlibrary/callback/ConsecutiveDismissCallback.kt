package com.pbworkshop.snackbarlibrary.callback

import com.google.android.material.snackbar.Snackbar

interface ConsecutiveDismissCallback {
    fun onDismissedAfterAnotherShown(snackbar: Snackbar?)
}