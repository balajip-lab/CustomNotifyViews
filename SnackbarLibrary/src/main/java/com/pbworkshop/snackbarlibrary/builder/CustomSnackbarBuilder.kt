package com.pbworkshop.snackbarlibrary.builder

import android.app.Activity
import android.content.Context
import android.content.res.TypedArray
import android.graphics.drawable.Drawable
import android.text.Spannable
import android.text.SpannableString
import android.text.SpannableStringBuilder
import android.text.style.ForegroundColorSpan
import android.view.View
import androidx.annotation.*
import androidx.core.content.ContextCompat
import com.google.android.material.snackbar.Snackbar
import com.pbworkshop.snackbarlibrary.R
import com.pbworkshop.snackbarlibrary.callback.*
import com.pbworkshop.snackbarlibrary.parent.ParentFinder
import com.pbworkshop.snackbarlibrary.wrapper.CustomSnackbarWrapper


class CustomSnackbarBuilder {
    var context: Context? = null
    var parentView: View
    var appendMsg: SpannableStringBuilder? = null
    var msg: CharSequence? = null
    var duration = Snackbar.LENGTH_LONG
    var actionTxt: CharSequence? = null
    var actionClickListener: View.OnClickListener? = null
    var callbacks: MutableList<Snackbar.Callback> = mutableListOf()
    var actionAllCaps = true
    var bgColor = 0
    var actionTxtColor = 0
    var msgTxtColor = 0
    var parentViewId = 0
    var icon: Drawable? = null
    var iconMargin = 0

    //view to attach the snackbar
    constructor(view: View) {
        setup(view.context)
        parentView = view
    }

    //attach to the activity with specific view id
    constructor(activity: Activity) {
        setup(activity)
        parentView = activity.findViewById(parentViewId)
    }

    //Attach to the activity parentview
    constructor(activity: Activity, parentFinder: ParentFinder) {
        setup(activity)
        parentView = parentFinder.findSnackbarParent(activity)
    }

    private fun setup(context: Context) {
        this.context = context
        loadThemeAttr()
    }

    //Text To Display
    fun msg(msg: CharSequence?): CustomSnackbarBuilder {
        this.msg = msg
        return this
    }

    //Text for strings to display
    fun msg(@StringRes msgId: Int): CustomSnackbarBuilder {
        msg = context!!.getString(msgId)
        return this
    }

    //Message Color
    fun msgTxtColor(@ColorInt msgTxtColor: Int): CustomSnackbarBuilder {
        this.msgTxtColor = msgTxtColor
        return this
    }

    //Message Color from resource
    fun msgTxtColorFrmRes(@ColorRes msgTxtColor: Int): CustomSnackbarBuilder {
        this.msgTxtColor = msgTxtColor
        return this
    }

    //Append msg with snackbar msg
    fun appendMsg(msg: CharSequence): CustomSnackbarBuilder{
        initAppendMsg()
        appendMsg?.append(msg)
        return this
    }

    //append msg from res
    fun appendMsg(@StringRes msgId: Int): CustomSnackbarBuilder? {
        return context?.let { appendMsg(it.getString(msgId)) }
    }

    //append msg with color
    fun appendMsg(msg: CharSequence, @ColorInt color: Int) : CustomSnackbarBuilder {
        initAppendMsg()
        val spannable: Spannable = SpannableString(msg)
        spannable.setSpan(
            ForegroundColorSpan(color),
            0,
            spannable.length,
            Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
        )
        appendMsg?.append(spannable)
        return this
    }

    //append msg with color from strings and colors
     fun appendMsg(@StringRes messageResId: Int, @ColorRes colorResId: Int): CustomSnackbarBuilder? {
        return getColor(colorResId)?.let { appendMsg(context!!.getString(messageResId), it) }
    }

    //duration to show the Snackbar
     fun duration(duration: Int): CustomSnackbarBuilder {
        this.duration = duration
        return this
    }

     //text for action
     fun actionTxt(actionText: CharSequence?): CustomSnackbarBuilder {
         actionTxt = actionText
         return this
     }

     //action text from res
     fun actionTxt(@StringRes actionTxtId: Int): CustomSnackbarBuilder{
         actionTxt = context?.getString(actionTxtId)
         return this
     }

     //action txt color
     fun actionTxtColor(@ColorInt color: Int): CustomSnackbarBuilder {
         actionTxtColor = color
         return this
     }

     //action txt color from res
     fun actionTxtColorRes(@ColorRes colorId: Int): CustomSnackbarBuilder{
        actionTxtColor = getColor(colorId)!!
         return this
     }

     //clickListener
     fun actionClickListener(onClickListener: View.OnClickListener) : CustomSnackbarBuilder{
         actionClickListener = onClickListener
         return this
     }

     //backgroundColor
     fun backgroundColor(@ColorInt color: Int): CustomSnackbarBuilder{
         bgColor = color
         return this
     }

     //bgcolor from res

     fun backgroundColorRes(@ColorRes colorId: Int): CustomSnackbarBuilder{
         bgColor = getColor(colorId)!!
         return this
     }

    //Snackbar default callback
     fun callback(callback: Snackbar.Callback) : CustomSnackbarBuilder{
        callbacks.add(callback)
        return this
     }

     //SBarCallback
     fun snackbarCallback(callback: SnackbarCallbacks): CustomSnackbarBuilder{
        callbacks.add(callback)
         return this
     }

     //Show Snackbar Callback
     fun showSnackbar(callback: ShowCallback): CustomSnackbarBuilder{
         callbacks.add(object : SnackbarCallbacks() {
             fun onSnackbarShown(snackbar: Snackbar?) {
                 callback.onShown(snackbar)
             }
         })
         return this
     }

     //Dismiss Snackbar Callback
     fun dismissSnackbar(callback: DismissCallback):CustomSnackbarBuilder{
         callbacks.add(object : SnackbarCallbacks() {
             fun onSnackbarDismiss(snackbar: Snackbar, dismissEvent: Int) {
                 callback.onDismissed(snackbar, dismissEvent)
             }
         })
         return this
     }

     //Action Dismiss Callback
     fun actionSnackbarDismiss(callback: ActionDismissCallback):CustomSnackbarBuilder{
         callbacks.add(object : SnackbarCallbacks() {
             fun onDismissbyAction(snackbar: Snackbar) {
                 callback.onActionPressed(snackbar)
             }
         })
         return this
     }

     //Swipe Dismiss Callback
     fun swipeDismissCallback(callback: SwipeDismissCallback) : CustomSnackbarBuilder{
         callbacks.add(object : SnackbarCallbacks() {
             fun onDismissbySwipe(snackbar: Snackbar) {
                 callback.onSwiped(snackbar)
             }
         })
         return this
     }

     //TimeOutDismiss Callback
     fun timeoutDismissCallback(callback: TimeoutDismissCallback): CustomSnackbarBuilder{
         callbacks.add(object : SnackbarCallbacks() {
             fun onDismissbyTimeOut(snackbar: Snackbar) {
                 callback.onTimedOut(snackbar)
             }
         })
        return this
     }

     //ManuallyDismiss Callback
     fun manualDismissCallback(callback: ManualDismissCallback) : CustomSnackbarBuilder{
         callbacks.add(object : SnackbarCallbacks() {
             fun onDismissbyManual(snackbar: Snackbar) {
                 callback.onManuallyDismissed(snackbar)
             }
         })
         return this
     }

     //inticate dismiss snackbar when another snackbar showing
      fun consecutiveDismissCallback(callback: ConsecutiveDismissCallback): CustomSnackbarBuilder{
          callbacks.add(object : SnackbarCallbacks() {
              fun onDismissbyConsecutive(snackbar: Snackbar) {
                  callback.onDismissedAfterAnotherShown(snackbar)
              }
          })
         return this
      }

     //Set Action text upper and lower cases
     fun actionUpperCase(upperCase: Boolean):CustomSnackbarBuilder{
         this.actionAllCaps = upperCase
         return this
     }

     //set icon to the message
     fun icon(icon: Drawable) : CustomSnackbarBuilder{
         this.icon = icon
         return this
     }

     //set icon to message from reasource
     fun icon(@DrawableRes resId: Int) : CustomSnackbarBuilder{
         this.icon = getDrawable(resId)
         return this
     }

    //icon margin
     fun iconMargin(iconMargin: Int): CustomSnackbarBuilder{
         this.iconMargin = iconMargin
        return this
     }

     //icon margin from res
     fun iconMarginRes(@DimenRes iconMarginResId: Int): CustomSnackbarBuilder{
         return context?.resources?.getDimensionPixelSize(iconMarginResId)?.let { iconMargin(it) }!!
     }

    //A wrapper class for future customisation

    fun buildWrapper(): CustomSnackbarWrapper {
        val snackbar = Snackbar.make(parentView, msg.toString(), duration)
        val wrapper: CustomSnackbarWrapper = CustomSnackbarWrapper(snackbar)
            .setAction(actionTxt, sanitisedActionClickListener())
            .setActionTextAllCaps(actionAllCaps)
            .addCallbacks(callbacks)
            .setIconMargin(iconMargin)
        when {
            actionTxtColor != 0 -> {
                wrapper.setActionTextColor(actionTxtColor)
            }
        }
        when {
            msgTxtColor != 0 -> {
                wrapper.setTextColor(msgTxtColor)
            }
        }
        when {
            appendMsg != null -> {
                wrapper.appendMessage(appendMsg!!)
            }
        }
        when {
            bgColor != 0 -> {
                wrapper.setBackgroundColor(bgColor);
            }
        }
        when {
            icon != null -> {
                wrapper.setIcon(icon);
            }
        }
        return wrapper;
    }

    private fun sanitisedActionClickListener(): View.OnClickListener? {
        return when (actionClickListener) {
            null -> {
                View.OnClickListener {
                    // Not needed
                }
            }
            else -> actionClickListener
        }
    }

    fun build(): Snackbar = buildWrapper().snackbar

    private fun getDrawable(@DrawableRes resId: Int): Drawable? {
        return context?.let { ContextCompat.getDrawable(it, resId) }
     }


     private fun getColor(@ColorRes colorResId: Int): Int? {
        return context?.let { ContextCompat.getColor(it, colorResId) };
    }


    private fun initAppendMsg() {
        when (appendMsg) {
            null -> {
                appendMsg = SpannableStringBuilder()
            }
        }
    }

    private fun loadThemeAttr() {
        val attrs = context!!.obtainStyledAttributes(null, R.styleable.CustomSnackbarBuilderStyle, R.attr.customSnackbarBuilderStyle, 0)
        try {
            loadMessageTextColor(attrs)
            loadActionTextColor(attrs)
            loadParentViewId(attrs)
            loadDuration(attrs)
            loadBackgroundColor(attrs)
            loadIconMargin(attrs)
            loadActionAllCaps(attrs)
        } finally {
            attrs.recycle()
        }
    }

    private fun loadActionAllCaps(attrs: TypedArray) {
        actionAllCaps = attrs.getBoolean(R.styleable.CustomSnackbarBuilderStyle_customSnackbarBuilder_actionAllCaps, true);
    }

    private fun loadIconMargin(attrs: TypedArray) {
        iconMargin = context?.resources
            ?.getDimensionPixelSize(R.dimen.customsnackbarbuilder_icon_margin_default)
            ?.let {
                attrs.getDimensionPixelSize(
                    R.styleable.CustomSnackbarBuilderStyle_customSnackbarBuilder_iconMargin, it
                )
            }!!
    }

    private fun loadBackgroundColor(attrs: TypedArray) {
        bgColor = attrs.getColor(R.styleable.CustomSnackbarBuilderStyle_customSnackbarBuilder_bgColor, 0)
    }

    private fun loadDuration(attrs: TypedArray) {
        duration = attrs.getInteger(
            R.styleable.CustomSnackbarBuilderStyle_customSnackbarBuilder_duration, Snackbar.LENGTH_LONG)
    }

    private fun loadParentViewId(attrs: TypedArray) {
        parentViewId = attrs.getResourceId(
            R.styleable.CustomSnackbarBuilderStyle_customSnackbarBuilder_parentViewId, 0)
    }

    private fun loadActionTextColor(attrs: TypedArray) {
        actionTxtColor = attrs.getColor(
            R.styleable.CustomSnackbarBuilderStyle_customSnackbarBuilder_actionTxtColor, 0)
    }

    private fun loadMessageTextColor(attrs: TypedArray) {
        msgTxtColor = attrs.getColor( R.styleable.CustomSnackbarBuilderStyle_customSnackbarBuilder_msgTxtColor, 0)
    }
}