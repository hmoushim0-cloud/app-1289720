package sa.factory.smartfoodsubscriptions.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import sa.factory.smartfoodsubscriptions.model.SubscriptionOption

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FoodSubscriptionPlanHomeScreen(
    viewModel: FoodSubscriptionPlanViewModel,
    onSelectItem: (SubscriptionOption) -> Unit,
    onQuickCheckout: () -> Unit
) {
    val catalog by viewModel.catalog.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Column {
                        Text("اشتراكات الغذاء الذكي", fontWeight = FontWeight.Bold, fontSize = 18.sp)
                        Text("حل اشتراكات غذائية يرفع رضا العملاء", fontSize = 12.sp, color = Color.Gray)
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.surface
                )
            )
        }
    ) { padding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(14.dp)
        ) {
            item {
                // Bespoke Vector Graphic Header (>= 8 Paths branched on archetype)
                BespokeVectorIllustration(
                    archetype = "cupertino_storefront",
                    screenIdx = 1,
                    modifier = Modifier.fillMaxWidth().height(160.dp)
                )
            }

            
            item {
                Text("المجموعات المختارة (Cupertino Storefront):", fontWeight = FontWeight.Bold, fontSize = 14.sp)
                Spacer(modifier = Modifier.height(8.dp))
                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    listOf("الأكثر طلباً", "وصل حديثاً", "عروض أبل").forEachIndexed { idx, cat ->
                        FilterChip(
                            selected = (idx == 0),
                            onClick = { },
                            label = { Text(cat, fontSize = 12.sp) }
                        )
                    }
                }
            }

            item {
                Surface(
                    shape = RoundedCornerShape(16.dp),
                    color = MaterialTheme.colorScheme.surfaceVariant,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Text("الدفع السريع بلمسة واحدة عبر Apple Pay", fontWeight = FontWeight.Bold, fontSize = 15.sp)
                        Text("ضمان أمان المعاملات وتوافق تام مع فواتير ZATCA الضريبية", fontSize = 12.sp, color = Color.Gray)
                        Spacer(modifier = Modifier.height(10.dp))
                        Button(onClick = onQuickCheckout, modifier = Modifier.fillMaxWidth(), shape = RoundedCornerShape(12.dp)) {
                            Text("شراء سريع بـ Apple Pay", fontWeight = FontWeight.Bold)
                        }
                    }
                }
            }
        

            item {
                Text("قائمة الخدمات المعتمدة للتخصيص:", fontWeight = FontWeight.Bold, fontSize = 14.sp)
            }

            items(catalog) { item ->
                Card(
                    shape = RoundedCornerShape(16.dp),
                    modifier = Modifier.fillMaxWidth(),
                    elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Column(modifier = Modifier.weight(1f)) {
                            Text(item.title, fontWeight = FontWeight.Bold, fontSize = 15.sp)
                            Text(item.description, color = Color.Gray, fontSize = 12.sp)
                        }
                        Button(
                            onClick = { onSelectItem(item) },
                            shape = RoundedCornerShape(10.dp)
                        ) {
                            Text("${item.priceMinorUnits / 100} SAR")
                        }
                    }
                }
            }
        }
    }
}
