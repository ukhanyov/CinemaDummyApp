package com.example.cinemadummyapp

import androidx.lifecycle.ViewModel
import com.example.cinemadummyapp.common.tickets.Ticket
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.getAndUpdate

class MainViewModel : ViewModel() {

    private val _boughtTickets = MutableStateFlow<List<Ticket>>(emptyList())
    val boughtTickets: StateFlow<List<Ticket>> = _boughtTickets.asStateFlow()

    private val _cartTickets = MutableStateFlow<List<Ticket>>(emptyList())
    val cartTickets: StateFlow<List<Ticket>> = _cartTickets.asStateFlow()

    fun addToCart(newTickets: List<Ticket>) {
        _cartTickets.getAndUpdate { it.toMutableList().apply { addAll(newTickets) } }
    }

    fun checkoutSuccess() {
        cartTickets.value.let { tickets ->
            if (tickets.isEmpty()) return
            _boughtTickets.getAndUpdate { it.toMutableList().apply { addAll(tickets) } }
        }
    }

}