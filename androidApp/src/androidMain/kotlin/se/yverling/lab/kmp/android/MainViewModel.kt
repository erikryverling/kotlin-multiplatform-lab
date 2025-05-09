package se.yverling.lab.kmp.android

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import se.yverling.lab.kmp.shared.Messages

class MainViewModel : ViewModel() {
    private val _messages = MutableStateFlow<List<String>>(listOf())
    val messages: StateFlow<List<String>> get() = _messages

    init {
        viewModelScope.launch {
            Messages.asFlow().collect { message ->
                _messages.update { list -> list + message }
            }
        }
    }
}
