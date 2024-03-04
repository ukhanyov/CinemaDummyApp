package com.example.cinemadummyapp.common

fun isValidEmail(email: String): Boolean {
    if (email.isEmpty()) return true
    val emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\$"
    return email.matches(emailRegex.toRegex())
}