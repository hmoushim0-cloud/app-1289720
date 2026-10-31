package sa.factory.smartfoodsubscriptions.model

import kotlinx.serialization.Serializable

@Serializable
data class SubscriptionOption(
    val id: String,
    val title: String,
    val description: String,
    val priceMinorUnits: Long,
    val iconVector: String = "verified"
)

@Serializable
data class FoodSubscriptionPlan(
    val requestId: String,
    val items: List<SubscriptionOption>,
    val customerPhone: String,
    val totalAmountMinorUnits: Long,
    val status: FoodSubscriptionPlanStatus = FoodSubscriptionPlanStatus.PENDING,
    val createdAtEpochMs: Long = System.currentTimeMillis()
)

enum class FoodSubscriptionPlanStatus {
    PENDING,
    ACCEPTED,
    IN_PROGRESS,
    COMPLETED,
    CANCELLED
}
