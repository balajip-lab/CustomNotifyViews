package com.pbworkshop.snackbarlibrary.callback

import com.google.android.material.snackbar.Snackbar

open class SnackbarCallbacks : Snackbar.Callback() {
    private fun SnackbarCallbacks(){
        // To prevent class being instantiated directly.
    }

    override fun onDismissed(transientBottomBar: Snackbar?, event: Int) {
        super.onDismissed(transientBottomBar, event)
        notifySnackbarCallback(transientBottomBar,event)
    }

    private fun notifySnackbarCallback(transientBottomBar: Snackbar?, event: Int) {
        when (event){
            DISMISS_EVENT_ACTION ->{
                onActionPressed(transientBottomBar)
            }
            DISMISS_EVENT_SWIPE->{
                onSwiped(transientBottomBar)
            }
            DISMISS_EVENT_CONSECUTIVE->{
                onDismissedAfterAnotherShown(transientBottomBar)
            }
            DISMISS_EVENT_MANUAL->{
                onManuallyDismissed(transientBottomBar)
            }
            DISMISS_EVENT_TIMEOUT->{
                onTimeOut(transientBottomBar)
            }
        }
        onSnackbarDismissed(transientBottomBar)
        onSnackbarDismissed(transientBottomBar, event)
    }

     fun onSnackbarDismissed(transientBottomBar: Snackbar?, event: Int) {
        // Override if needed
    }

     fun onSnackbarDismissed(transientBottomBar: Snackbar?) {
        // Override if needed
    }

    fun onActionPressed(snackbar:Snackbar?){
        // Override if needed
    }

    fun onTimeOut(snackbar: Snackbar?){
        // Override if needed
    }

    fun onSwiped(snackbar: Snackbar?){
        // Override if needed
    }

    fun onManuallyDismissed(snackbar: Snackbar?){
        // Override if needed
    }

    fun onDismissedAfterAnotherShown(snackbar: Snackbar?){
        // Override if needed
    }

    override fun onShown(sb: Snackbar?) {
        super.onShown(sb)
        onSnackbarShown(sb)
    }

    private fun onSnackbarShown(snackbar: Snackbar?) {
        // Override if needed
    }
}