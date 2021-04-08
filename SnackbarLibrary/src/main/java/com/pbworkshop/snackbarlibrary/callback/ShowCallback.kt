package com.pbworkshop.snackbarlibrary.callback

import com.google.android.material.snackbar.Snackbar

interface ShowCallback {
    fun onShown(snackbar: Snackbar?)
}