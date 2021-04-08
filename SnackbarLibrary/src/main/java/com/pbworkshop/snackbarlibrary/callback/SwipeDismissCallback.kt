package com.pbworkshop.snackbarlibrary.callback

import com.google.android.material.snackbar.Snackbar

interface SwipeDismissCallback {
    fun onSwiped(snackbar: Snackbar?)
}