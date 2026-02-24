package com.example.financeapp.ui.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.pager.*
import androidx.compose.foundation.shape.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.*
import com.example.financeapp.data.*
import com.example.financeapp.ui.theme.*
import kotlinx.coroutines.launch

@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun OnboardingScreen(
    onNavigateToHome: () -> Unit,
) {
    val pageCount = onboardingData.size
    val pageState = rememberPagerState() { pageCount }
    val currentIndex = pageState.currentPage
    val isLast = pageState.currentPage == (pageCount - 1);
    val animationScope = rememberCoroutineScope()

    Scaffold(containerColor = Color.White) { innerPadding ->
        Column(
            modifier = Modifier.padding(innerPadding),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.weight(1f))
            HorizontalPager(state = pageState, userScrollEnabled = false) { index ->
                OnboardingPage(onboardingData[index].image)
            }
            DotIndicator(pageCount, currentIndex)
            Text(
                onboardingData[currentIndex].title,
                fontSize = 24.sp,
                fontWeight = FontWeight.W600,
                modifier = Modifier.padding(vertical = 16.dp)
            )
            Text(
                onboardingData[currentIndex].desc,
                fontSize = 12.sp,
                color = Color.Gray,
                modifier = Modifier.padding(horizontal = 56.dp),
                textAlign = TextAlign.Center,
                lineHeight = 15.sp
            )
            Spacer(modifier = Modifier.weight(1f))
            if (!isLast)
                CustomButton(
                    label = "Next",
                    onClick = {
                        animationScope.launch {
                            pageState.animateScrollToPage(currentIndex + 1)
                        }
                    }
                )
            if (isLast) CustomButton(
                label = "Go to home",
                onClick = onNavigateToHome
            )
            else CustomButton(
                label = "Skip",
                onClick = {
                    animationScope.launch {
                        pageState.animateScrollToPage(pageCount - 1)
                    }
                },
                isBordered = true
            )
            Spacer(modifier = Modifier.weight(1f))
        }
    }
}

@Composable
fun OnboardingPage(image: Int, modifier: Modifier = Modifier) {
    Image(
        painter = painterResource(image),
        contentDescription = "Onboarding",
        modifier = modifier
            .fillMaxWidth()
            .padding(bottom = 16.dp),
        contentScale = ContentScale.FillWidth
    )
}

@Composable
fun DotIndicator(totalIndex: Int, currentIndex: Int) {
    Row(horizontalArrangement = Arrangement.Center) {
        repeat(totalIndex) { index ->
            Box(
                modifier = Modifier
                    .size(16.dp, 4.dp)
                    .padding(horizontal = 2.dp)
                    .clip(CircleShape)
                    .background(
                        color = if (index == currentIndex) green2 else Color.LightGray.copy(
                            0.5f
                        )
                    )
            )
        }
    }
}

@Composable
fun CustomButton(label: String, onClick: () -> Unit, isBordered: Boolean = false) {
    val containerColor = if (isBordered) Color.White else green
    val contentColor = if (isBordered) Color.Black else green2
    val border = BorderStroke(1.dp, color = Color.LightGray.copy(0.5f))

    Button(
        onClick = onClick,
        border = if (isBordered) border else null,
        colors = ButtonColors(
            containerColor, contentColor, Color.LightGray, Color.Gray
        ),
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 2.dp),
        shape = RoundedCornerShape(12.dp)
    ) {
        Text(label, fontSize = 16.sp, modifier = Modifier.padding(vertical = 4.dp))
    }
}