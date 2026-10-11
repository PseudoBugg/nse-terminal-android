package com.aarontechnology.nseterminal.ui.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.aarontechnology.nseterminal.data.MonthlyMarketCapPoint
import com.aarontechnology.nseterminal.ui.theme.*
import java.util.Locale

@Composable
fun MarketCapChart(
    data: List<MonthlyMarketCapPoint>,
    modifier: Modifier = Modifier
) {
    if (data.isEmpty()) {
        Box(
            modifier = modifier.fillMaxWidth().height(260.dp),
            contentAlignment = Alignment.Center
        ) {
            Text("No Market Cap Data Available", color = FgMuted, fontSize = 14.sp)
        }
        return
    }

    var selectedPoint by remember { mutableStateOf<MonthlyMarketCapPoint?>(null) }
    val latestPoint = data.last()
    val peakPoint = data.maxByOrNull { it.avgMarketCapCr } ?: latestPoint
    val troughPoint = data.minByOrNull { it.avgMarketCapCr } ?: latestPoint

    Column(modifier = modifier.fillMaxWidth()) {
        // Top HUD Bar with Peak/Trough & Touch Inspection Banner
        Card(
            colors = CardDefaults.cardColors(containerColor = BgHeader),
            shape = RoundedCornerShape(8.dp),
            modifier = Modifier.fillMaxWidth().padding(horizontal = 8.dp, vertical = 4.dp)
        ) {
            Column(modifier = Modifier.padding(10.dp)) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = "Current MCap: ₹${String.format(Locale.US, "%,.0f", latestPoint.avgMarketCapCr)} Cr",
                        color = AccentCyan,
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = "10Y Peak: ₹${String.format(Locale.US, "%,.0f", peakPoint.avgMarketCapCr)} Cr (${peakPoint.monthName})",
                        color = AccentAmber,
                        fontSize = 12.sp,
                        fontWeight = FontWeight.SemiBold
                    )
                }

                Spacer(modifier = Modifier.height(4.dp))

                // Interactive Inspection Status Bar
                if (selectedPoint != null) {
                    val pt = selectedPoint!!
                    Text(
                        text = "📅 Month: ${pt.monthName}  |  💰 Avg MCap: ₹${String.format(Locale.US, "%,.0f", pt.avgMarketCapCr)} Cr  |  📈 Price: ₹${String.format(Locale.US, "%.2f", pt.avgPrice)}",
                        color = AccentGreen,
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Bold
                    )
                } else {
                    Text(
                        text = "⚡ TOUCH OR DRAG ACROSS CHART TO INSPECT MONTHLY AVERAGE MARKET CAP",
                        color = FgMuted,
                        fontSize = 11.sp,
                        fontWeight = FontWeight.Medium
                    )
                }
            }
        }

        // Native High-Tech Compose Canvas Area Chart
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(280.dp)
                .padding(8.dp)
                .background(BgDark, RoundedCornerShape(8.dp))
        ) {
            Canvas(
                modifier = Modifier
                    .fillMaxSize()
                    .pointerInput(data) {
                        detectTapGestures { offset ->
                            val padLeft = 70f
                            val padRight = 30f
                            val chartWidth = size.width - padLeft - padRight
                            if (offset.x in padLeft..(size.width - padRight)) {
                                val frac = ((offset.x - padLeft) / chartWidth).coerceIn(0f, 1f)
                                val index = (frac * (data.size - 1)).toInt().coerceIn(0, data.size - 1)
                                selectedPoint = data[index]
                            }
                        }
                    }
                    .pointerInput(data) {
                        detectDragGestures(
                            onDrag = { change, _ ->
                                val padLeft = 70f
                                val padRight = 30f
                                val chartWidth = size.width - padLeft - padRight
                                val x = change.position.x
                                if (x in padLeft..(size.width - padRight)) {
                                    val frac = ((x - padLeft) / chartWidth).coerceIn(0f, 1f)
                                    val index = (frac * (data.size - 1)).toInt().coerceIn(0, data.size - 1)
                                    selectedPoint = data[index]
                                }
                            },
                            onDragEnd = { /* keep last touched point visible */ }
                        )
                    }
            ) {
                val padLeft = 70f
                val padRight = 30f
                val padTop = 30f
                val padBottom = 40f

                val chartW = size.width - padLeft - padRight
                val chartH = size.height - padTop - padBottom

                val maxVal = (data.maxOfOrNull { it.avgMarketCapCr } ?: 1000.0) * 1.15
                val minVal = (data.minOfOrNull { it.avgMarketCapCr } ?: 0.0) * 0.85
                val valRange = (maxVal - minVal).coerceAtLeast(1.0)

                // 1. Gridlines
                val numGrid = 4
                for (i in 0..numGrid) {
                    val y = padTop + chartH - (i.toFloat() / numGrid) * chartH
                    drawLine(
                        color = BorderColor,
                        start = Offset(padLeft, y),
                        end = Offset(padLeft + chartW, y),
                        strokeWidth = 1f,
                        pathEffect = PathEffect.dashPathEffect(floatArrayOf(6f, 6f), 0f)
                    )
                }

                // 2. Map coordinates
                val points = data.mapIndexed { idx, pt ->
                    val x = padLeft + (idx.toFloat() / (data.size - 1)) * chartW
                    val y = padTop + chartH - (((pt.avgMarketCapCr - minVal) / valRange).toFloat() * chartH)
                    Offset(x, y)
                }

                // 3. Area gradient fill
                if (points.size > 1) {
                    val fillPath = Path().apply {
                        moveTo(points.first().x, padTop + chartH)
                        points.forEach { lineTo(it.x, it.y) }
                        lineTo(points.last().x, padTop + chartH)
                        close()
                    }
                    drawPath(
                        path = fillPath,
                        brush = Brush.verticalGradient(
                            colors = listOf(Color(0x5538BDF8), Color(0x05090D16)),
                            startY = padTop,
                            endY = padTop + chartH
                        )
                    )
                }

                // 4. Line Chart
                if (points.size > 1) {
                    val linePath = Path().apply {
                        moveTo(points.first().x, points.first().y)
                        for (i in 1 until points.size) {
                            lineTo(points[i].x, points[i].y)
                        }
                    }
                    drawPath(
                        path = linePath,
                        color = AccentCyan,
                        style = Stroke(width = 3.dp.toPx())
                    )
                }

                // 5. Interactive Touch Marker
                if (selectedPoint != null) {
                    val idx = data.indexOf(selectedPoint)
                    if (idx != -1 && idx < points.size) {
                        val selOffset = points[idx]
                        // Vertical crosshair
                        drawLine(
                            color = AccentCyan,
                            start = Offset(selOffset.x, padTop),
                            end = Offset(selOffset.x, padTop + chartH),
                            strokeWidth = 2f,
                            pathEffect = PathEffect.dashPathEffect(floatArrayOf(8f, 8f), 0f)
                        )
                        // Glowing outer circle
                        drawCircle(
                            color = AccentCyan.copy(alpha = 0.4f),
                            radius = 10.dp.toPx(),
                            center = selOffset
                        )
                        // Inner circle
                        drawCircle(
                            color = AccentCyan,
                            radius = 5.dp.toPx(),
                            center = selOffset
                        )
                        drawCircle(
                            color = Color.White,
                            radius = 2.dp.toPx(),
                            center = selOffset
                        )
                    }
                }
            }
        }
    }
}
