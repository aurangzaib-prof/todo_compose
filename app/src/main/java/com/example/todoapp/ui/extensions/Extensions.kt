package com.example.todoapp.ui.extensions

import android.content.Context
import android.widget.Toast
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

fun Context.toast(message: String, duration: Int = Toast.LENGTH_SHORT) {
    Toast.makeText(this, message, duration).show()
}

fun Long?.toFormattedDate(pattern: String = "dd/MM/yyyy"): String {
    if (this == null) return ""
    val formatter = SimpleDateFormat(pattern, Locale.getDefault())
    return formatter.format(Date(this))
}


fun String.isValidEmail(): Boolean {
    return android.util.Patterns.EMAIL_ADDRESS.matcher(this).matches()
}

fun formatTime(hour: Int, minute: Int): String {
    val amPm = if (hour < 12) "AM" else "PM"
    val adjustedHour = if (hour % 12 == 0) 12 else hour % 12
    return String.format("%02d:%02d %s", adjustedHour, minute, amPm)
}
