package sa.factory.smartfoodsubscriptions.data

import sa.factory.smartfoodsubscriptions.model.FoodSubscriptionPlan
import sa.factory.smartfoodsubscriptions.model.SubscriptionOption
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

interface FoodSubscriptionPlanRepository {
    fun getAvailableCatalog(): Flow<List<SubscriptionOption>>
    fun getActiveRequest(): Flow<FoodSubscriptionPlan?>
    suspend fun submitRequest(request: FoodSubscriptionPlan): Result<String>
}

class InMemoryFoodSubscriptionPlanRepository : FoodSubscriptionPlanRepository {
    private val _catalog = MutableStateFlow<List<SubscriptionOption>>(listOf(
        SubscriptionOption("1", "خطة أسبوعية", "تسليم وجبات صحية ومتوازنة أسبوعياً مع خيارات تخصيص حسب الاحتياجات الغذائية", 5000L, "verified"),
        SubscriptionOption("2", "خطة شهرية", "تسليم وجبات متكاملة شهرياً مع تخفيضات خاصة للعضوية الدائمة", 9500L, "bolt")
    ))
    private val _active = MutableStateFlow<FoodSubscriptionPlan?>(null)

    override fun getAvailableCatalog(): Flow<List<SubscriptionOption>> = _catalog.asStateFlow()
    override fun getActiveRequest(): Flow<FoodSubscriptionPlan?> = _active.asStateFlow()

    override suspend fun submitRequest(request: FoodSubscriptionPlan): Result<String> {
        _active.value = request
        return Result.success(request.requestId)
    }
}
