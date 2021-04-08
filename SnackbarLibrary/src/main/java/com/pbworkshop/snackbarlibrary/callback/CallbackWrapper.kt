package com.pbworkshop.snackbarlibrary.callback

import com.google.android.material.snackbar.Snackbar

internal open class CallbackWrapper(private var callback: Snackbar.Callback?) : Snackbar.Callback() {

    override fun onShown(sb: Snackbar) {
        super.onShown(sb)
        callback!!.onShown(sb)
    }
    override fun onDismissed(transientBottomBar: Snackbar, event: Int) {
        super.onDismissed(transientBottomBar, event)
        if (callback != null) {
            callback!!.onDismissed(transientBottomBar, event)
        }
    }
}