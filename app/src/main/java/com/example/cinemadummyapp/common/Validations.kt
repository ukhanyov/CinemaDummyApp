package com.example.cinemadummyapp.common

fun String.isValidEmail(): Boolean {
    if (this.isEmpty()) return true
    val emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\$"
    return this.matches(emailRegex.toRegex())
}

fun String.isValidPassword(): Boolean {
    return this.isNotBlank()
}