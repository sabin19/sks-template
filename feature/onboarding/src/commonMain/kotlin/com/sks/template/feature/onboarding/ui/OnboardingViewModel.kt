package com.sks.template.feature.onboarding.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sks.template.core.domain.onboarding.GetOnboardingItemsUseCase
import com.sks.template.core.domain.onboarding.SetOnboardingCompletedUseCase
import com.sks.template.core.model.onboarding.OnboardingItem
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

sealed interface OnboardingUiState {
    data object Loading : OnboardingUiState
    data class Success(val items: List<OnboardingItem>, val currentIndex: Int = 0) : OnboardingUiState
}

class OnboardingViewModel(
    private val getOnboardingItemsUseCase: GetOnboardingItemsUseCase,
    private val setOnboardingCompletedUseCase: SetOnboardingCompletedUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow<OnboardingUiState>(OnboardingUiState.Loading)
    val uiState: StateFlow<OnboardingUiState> = _uiState.asStateFlow()

    init {
        loadOnboarding()
    }

    fun loadOnboarding() {
        viewModelScope.launch {
            val result = getOnboardingItemsUseCase(Unit)
            val items = result.getOrNull() ?: emptyList()
            _uiState.update { OnboardingUiState.Success(items = items) }
        }
    }

    fun completeOnboarding(onCompleted: () -> Unit) {
        viewModelScope.launch {
            setOnboardingCompletedUseCase(true)
            onCompleted()
        }
    }
}
