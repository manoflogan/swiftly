package com.krishnanand.mobile.swiftly.utils

import android.graphics.drawable.Drawable
import android.text.SpannableString
import android.text.Spanned
import android.text.style.StrikethroughSpan
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.squareup.picasso.Picasso

/**
 * Utility classes
 */
object Utils {

    @JvmStatic
    @BindingAdapter("imageUrl")
    fun setBitmapFromUrl(imageView: ImageView, imageUrlAsString: String) =
        Picasso.get().load(imageUrlAsString).into(imageView)

    @JvmStatic
    @BindingAdapter("inUSD")
    fun showMoneyInUSD(textView: TextView, money: Float) {
        textView.setText(buildString {
            append("%.2f".format(money)).append(" USD")
        })
    }

    @JvmStatic
    @BindingAdapter("showMoneyStrikeThrough")
    fun showMoneyStrikeThrough(textView: TextView, money: Float) {
        val str = buildString {
            append("%.2f".format(money)).append(" USD")
        }
        val spannableString = SpannableString(str)
        spannableString.setSpan(
            StrikethroughSpan(), 0, str.length, Spanned.SPAN_INCLUSIVE_EXCLUSIVE
        )
        textView.setText(
            spannableString
        )
    }
}