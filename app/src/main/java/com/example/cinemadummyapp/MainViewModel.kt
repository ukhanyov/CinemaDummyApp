package com.example.cinemadummyapp

import androidx.lifecycle.ViewModel
import com.example.cinemadummyapp.common.tickets.Ticket
import com.example.cinemadummyapp.interaction.payment.Card
import com.example.cinemadummyapp.interaction.payment.CardType
import kotlinx.coroutines.flow.*

class MainViewModel : ViewModel() {

    private val _boughtTickets = MutableStateFlow<List<Ticket>>(emptyList())
    val boughtTickets: StateFlow<List<Ticket>> = _boughtTickets.asStateFlow()

    private val _cartTickets = MutableStateFlow<List<Ticket>>(emptyList())
    val cartTickets: StateFlow<List<Ticket>> = _cartTickets.asStateFlow()

    private val _cards = MutableStateFlow(
        listOf(
            Card(
                cardNumber = "1234 4321 1234 4321",
                cardDate = "31/09",
                cardHolderName = "Your Name",
                cardType = CardType.Mastercard,
                cvv = "",
            ),
            Card(
                cardNumber = "1234 4321 1234 4321",
                cardDate = "31/09",
                cardHolderName = "Your Name",
                cardType = CardType.Visa,
                cvv = "",
            ),
        )
    )
    val cards: StateFlow<List<Card>> = _cards.asStateFlow()

    fun addToCart(newTickets: List<Ticket>) {
        _cartTickets.getAndUpdate { it.toMutableList().apply { addAll(newTickets) } }
    }

    fun removeFromCart(ticket: Ticket) {
        _cartTickets.getAndUpdate { it.toMutableList().apply { remove(ticket) } }
    }

    fun checkoutSuccess() {
        cartTickets.value.let { tickets ->
            if (tickets.isEmpty()) return
            _boughtTickets.getAndUpdate { it.toMutableList().apply { addAll(tickets) } }
            _cartTickets.update { emptyList() }
        }
    }

    fun addNewCard(card: Card) {
        _cards.getAndUpdate { it.toMutableList().apply { add(card) } }
    }

    fun selectCard(card: Card) {
        _cards.getAndUpdate {
            it.map { it.copy(isSelected = card.id == it.id) }
        }
    }

}