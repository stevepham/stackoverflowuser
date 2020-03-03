package com.ht117.sofossill.app

import android.graphics.Color
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.Glide
import com.ht117.sofossill.R
import java.text.DateFormat
import java.text.DecimalFormat
import java.time.Instant
import java.util.*

fun Long.displayTime(): String {
    return DateFormat.getDateInstance(DateFormat.MEDIUM).format(Date(this * 1000))
}

fun Long.formatNumber(): String {
    val formatter = DecimalFormat("###,###")
    return formatter.format(this)
}

fun ImageView.loadAvatar(url: String?) {
    val loader = CircularProgressDrawable(this.context).apply {
        strokeWidth = 5f
        centerRadius = 90f
        start()
    }

    Glide.with(this)
        .load(url)
        .circleCrop()
        .placeholder(loader)
        .error(R.mipmap.ic_launcher_round)
        .into(this)
}

fun View.showView() {
    visibility = View.VISIBLE
}

fun View.hideView() {
    visibility = View.GONE
}

fun TextView.showInfo(messageId: Int) {
    showView()
    setText(messageId)
    setTextColor(Color.BLUE)
}

fun TextView.showError(messageId: Int) {
    if (messageId != 0) {
        showView()
        setText(messageId)
        setTextColor(Color.RED)
    }
}