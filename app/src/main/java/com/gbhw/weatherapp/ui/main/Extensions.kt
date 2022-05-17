package com.gbhw.weatherapp.ui.main

import android.view.View
import android.widget.Toast
import androidx.annotation.StringRes
import com.google.android.material.snackbar.Snackbar

fun View.showToast(
    text: String,
    length: Int = Toast.LENGTH_SHORT
) {
    Toast.makeText(context, text, length).show()
}

fun View.showSnackBarWithAction(
    text: String,
    actionText: String,
    action: (View) -> Unit,
    length: Int = Snackbar.LENGTH_SHORT
) {
    Snackbar.make(this, text, length).setAction(actionText, action).show()
}

fun View.showSnackBar(
    text: String,
    length: Int = Snackbar.LENGTH_LONG
) {
    Snackbar.make(this, text, length).show()
}

fun View.showSnackBarWithResource(
    @StringRes text: Int,
    length: Int = Snackbar.LENGTH_INDEFINITE
) {
    Snackbar.make(this, text, length).show()
}
