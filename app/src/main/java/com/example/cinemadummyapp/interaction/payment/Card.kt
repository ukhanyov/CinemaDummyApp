package com.example.cinemadummyapp.interaction.payment

data class Card(
    val cardNumber: String,
    val cardDate: String,
    val cardHolderName: String,
    val cardType: CardType,
)

enum class CardType {
    Visa,
    Mastercard,
}