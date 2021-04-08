package com.pbworkshop.snackbarlibrary.extension

import android.annotation.SuppressLint
import android.os.Build
import android.widget.TextView
import androidx.appcompat.widget.AppCompatTextView

class TextViewExtension private constructor(private val textView: TextView) {
    @SuppressLint("NewApi")
    fun setAllCaps(allCaps: Boolean) {
        if (isApiAtLeast14) {
            textView.isAllCaps = allCaps
        }
    }

    private val isApiAtLeast14: Boolean
        get() = Build.VERSION.SDK_INT >= Build.VERSION_CODES.ICE_CREAM_SANDWICH

    companion object {
        @JvmStatic
        fun from(textView: TextView): TextViewExtension {
            return TextViewExtension(textView)
        }
    }
}