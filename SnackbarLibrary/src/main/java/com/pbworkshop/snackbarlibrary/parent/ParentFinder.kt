package com.pbworkshop.snackbarlibrary.parent

import android.app.Activity
import android.view.View

interface ParentFinder {
    fun findSnackbarParent(activity: Activity): View
}