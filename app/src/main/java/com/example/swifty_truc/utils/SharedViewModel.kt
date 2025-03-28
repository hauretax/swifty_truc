package com.example.swifty_truc.utils

import UserDTO
import androidx.lifecycle.ViewModel
import com.example.swifty_truc.services.ApiService

class SharedViewModel : ViewModel() {
    var user: UserDTO? = null
    var apiService: ApiService? = null
}
