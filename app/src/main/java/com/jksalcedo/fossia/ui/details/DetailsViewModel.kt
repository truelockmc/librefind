package com.jksalcedo.fossia.ui.details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jksalcedo.fossia.domain.model.Alternative
import com.jksalcedo.fossia.domain.usecase.GetAlternativeUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

/**
 * ViewModel for Details screen
 * 
 * Manages fetching and displaying alternatives for a proprietary app
 */
class DetailsViewModel(
    private val getAlternativeUseCase: GetAlternativeUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(DetailsState())
    val state: StateFlow<DetailsState> = _state.asStateFlow()

    fun loadAlternatives(packageName: String) {
        viewModelScope.launch {
            _state.update { it.copy(isLoading = true, error = null) }
            
            try {
                val alternatives = getAlternativeUseCase(packageName)
                _state.update {
                    it.copy(
                        isLoading = false,
                        packageName = packageName,
                        alternatives = alternatives,
                        error = null
                    )
                }
            } catch (e: Exception) {
                _state.update {
                    it.copy(
                        isLoading = false,
                        error = e.message ?: "Failed to load alternatives"
                    )
                }
            }
        }
    }

    fun retry(packageName : String) {
        loadAlternatives(
            packageName = packageName
        )
    }
}

/**
 * UI State for Details screen
 */
data class DetailsState(
    val isLoading: Boolean = false,
    val packageName: String = "",
    val alternatives: List<Alternative> = emptyList(),
    val error: String? = null
)
