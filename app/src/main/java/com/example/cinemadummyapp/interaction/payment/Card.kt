package com.example.cinemadummyapp.interaction.payment

import java.util.UUID

data class Card(
    val id: String = UUID.randomUUID().toString(),
    val cardNumber: String,
    val cardDate: String,
    val cardHolderName: String,
    val cardType: CardType,
    val isSelected: Boolean = false,
    val cardType2: CardType2 = CardType2.entries.toTypedArray().random(),
)

enum class CardType {
    Visa,
    Mastercard,
}

enum class CardType2 {
    Credit,
    Debit,
}