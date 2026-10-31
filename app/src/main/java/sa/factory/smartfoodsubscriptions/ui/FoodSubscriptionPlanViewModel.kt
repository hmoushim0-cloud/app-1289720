package sa.factory.smartfoodsubscriptions.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import sa.factory.smartfoodsubscriptions.data.FoodSubscriptionPlanRepository
import sa.factory.smartfoodsubscriptions.model.FoodSubscriptionPlan
import sa.factory.smartfoodsubscriptions.model.SubscriptionOption
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class FoodSubscriptionPlanViewModel(
    private val repository: FoodSubscriptionPlanRepository
) : ViewModel() {
    val catalog: StateFlow<List<SubscriptionOption>> = repository.getAvailableCatalog()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    val activeRequest: StateFlow<FoodSubscriptionPlan?> = repository.getActiveRequest()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), null)

    fun submitNewOrder(request: FoodSubscriptionPlan) {
        viewModelScope.launch {
            repository.submitRequest(request)
        }
    }
}
