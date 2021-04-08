package com.pbworkshop.snackbarlibrary.wrapper

import android.content.Context
import android.content.res.ColorStateList
import android.graphics.drawable.Drawable
import android.text.Spannable
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.annotation.*
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.AppCompatTextView
import androidx.core.content.ContextCompat
import com.google.android.material.snackbar.Snackbar
import com.pbworkshop.snackbarlibrary.R
import com.pbworkshop.snackbarlibrary.callback.SnackbarCallbacks
import com.pbworkshop.snackbarlibrary.extension.TextViewExtension
import com.pbworkshop.snackbarlibrary.extension.TextViewExtension.Companion.from

class CustomSnackbarWrapper(val snackbar: Snackbar) {
    var context: Context
    private val messageView: TextView
    private val actionView: Button
    private val actionViewExtension: TextViewExtension
    private val view: View
        get() = snackbar.view
    val actionText: CharSequence
        get() = actionView.text

    fun setActionText(@StringRes actionText: Int): CustomSnackbarWrapper {
        actionView.setText(actionText)
        return this
    }

    fun setActionText(actionText: CharSequence?): CustomSnackbarWrapper {
        actionView.text = actionText
        return this
    }

    fun setActionClickListener(actionClickListener: View.OnClickListener?): CustomSnackbarWrapper {
        actionView.setOnClickListener(actionClickListener)
        return this
    }

    fun setAction(
        @StringRes actionText: Int,
        actionClickListener: View.OnClickListener?
    ): CustomSnackbarWrapper {
        snackbar.setAction(actionText, actionClickListener)
        return this
    }

    fun setAction(
        actionText: CharSequence?,
        actionClickListener: View.OnClickListener?
    ): CustomSnackbarWrapper {
        snackbar.setAction(actionText, actionClickListener)
        return this
    }

    val actionTextColors: ColorStateList
        get() = actionView.textColors
    val actionCurrentTextColor: Int
        get() = actionView.currentTextColor

    fun setActionTextColor(colors: ColorStateList?): CustomSnackbarWrapper {
        snackbar.setActionTextColor(colors)
        return this
    }

    fun setActionTextColor(@ColorInt color: Int): CustomSnackbarWrapper {
        snackbar.setActionTextColor(color)
        return this
    }

    fun setActionTextColorRes(@ColorRes color: Int): CustomSnackbarWrapper {
        snackbar.setActionTextColor(ContextCompat.getColor(context, color))
        return this
    }

    val actionVisibility: Int
        get() = actionView.visibility

    fun setActionVisibility(visibility: Int): CustomSnackbarWrapper {
        actionView.visibility = visibility
        return this
    }

    fun setActionTextAllCaps(allCaps: Boolean): CustomSnackbarWrapper {
        actionViewExtension.setAllCaps(allCaps)
        return this
    }

    val text: CharSequence
        get() = messageView.text

    fun setText(@StringRes message: Int): CustomSnackbarWrapper {
        snackbar.setText(message)
        return this
    }

    fun setText(message: CharSequence): CustomSnackbarWrapper {
        snackbar.setText(message)
        return this
    }

    val textColors: ColorStateList
        get() = messageView.textColors
    val currentTextColor: Int
        get() = messageView.currentTextColor

    fun setTextColor(@ColorInt color: Int): CustomSnackbarWrapper {
        messageView.setTextColor(color)
        return this
    }

    fun setTextColor(colors: ColorStateList?): CustomSnackbarWrapper {
        messageView.setTextColor(colors)
        return this
    }

    fun setTextColorRes(@ColorRes color: Int): CustomSnackbarWrapper {
        messageView.setTextColor(ContextCompat.getColor(context, color))
        return this
    }

    fun appendMessage(message: CharSequence): CustomSnackbarWrapper {
        messageView.append(message)
        return this
    }

    fun appendMessage(@StringRes message: Int): CustomSnackbarWrapper {
        return appendMessage(context.getString(message))
    }

    fun appendMessage(message: CharSequence, @ColorInt color: Int): CustomSnackbarWrapper {
        val spannable: Spannable = SpannableString(message)
        spannable.setSpan(
            ForegroundColorSpan(color), 0, spannable.length,
            Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
        )
        messageView.append(spannable)
        return this
    }

    fun appendMessage(
        @StringRes message: Int,
        @ColorRes color: Int
    ): CustomSnackbarWrapper {
        return appendMessage(
            context.getString(message),
            ContextCompat.getColor(context, color)
        )
    }

    val messageVisibility: Int
        get() = messageView.visibility

    fun setMessageVisibility(visibility: Int): CustomSnackbarWrapper {
        messageView.visibility = visibility
        return this
    }

    val duration: Int
        get() = snackbar.duration

    fun setDuration(duration: Int): CustomSnackbarWrapper {
        snackbar.duration = duration
        return this
    }

    fun setBackgroundColor(@ColorInt color: Int): CustomSnackbarWrapper {
        view.setBackgroundColor(color)
        return this
    }

    fun setBackgroundColorRes(@ColorRes color: Int): CustomSnackbarWrapper {
        view.setBackgroundColor(ContextCompat.getColor(context, color))
        return this
    }

    fun addCallback(callback: Snackbar.Callback): CustomSnackbarWrapper {
        snackbar.addCallback(callback)
        return this
    }

    fun addCallbacks(callbacks: List<Snackbar.Callback>): CustomSnackbarWrapper {
        val callbacksSize = callbacks.size
        for (i in 0 until callbacksSize) {
            addCallback(callbacks[i])
        }
        return this
    }

    fun addSnackbarCallback(callback: SnackbarCallbacks): CustomSnackbarWrapper {
        return addCallback(callback)
    }

    // //setIcon from resource
    fun setIcon(@DrawableRes icon: Int): CustomSnackbarWrapper {
        return setIcon(ContextCompat.getDrawable(context, icon))
    }
    //setIcon
    fun setIcon(icon: Drawable?): CustomSnackbarWrapper {
        messageView.setCompoundDrawablesWithIntrinsicBounds(icon, null, null, null)
        return this
    }

    // //setIconMargin
    fun setIconMargin(iconMarginPixels: Int): CustomSnackbarWrapper {
        messageView.compoundDrawablePadding = iconMarginPixels
        return this
    }

    //setIconMargin from resource
    fun setIconMarginRes(@DimenRes iconMargin: Int): CustomSnackbarWrapper {
        return setIconMargin(context.resources.getDimensionPixelSize(iconMargin))
    }

    //Visible callback
    fun show(): CustomSnackbarWrapper {
        snackbar.show()
        return this
    }

    //Dismiss callback
    fun dismiss(): CustomSnackbarWrapper {
        snackbar.dismiss()
        return this
    }

    //Snack is visible
    val isShown: Boolean
        get() = snackbar.isShown

    //is snackbar visible or in queue
    val isShownOrQueued: Boolean
        get() = snackbar.isShownOrQueued

    init {
        messageView = view.findViewById<View>(R.id.snackbar_text) as TextView
        actionView = view.findViewById<View>(R.id.snackbar_action) as Button
        actionViewExtension = from(actionView)
        context = snackbar.view.context
    }
}