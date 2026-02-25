package com.example.financeapp.ui.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.*
import com.example.financeapp.data.*
import com.example.financeapp.ui.theme.*

@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun OnboardingScreen(onNavigateToHome: () -> Unit) {
    Scaffold(contentWindowInsets = WindowInsets.ime) { innerPadding ->
        Column(
            modifier = Modifier.padding(innerPadding),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            OnboardingImages(onboardingData.image)
            Spacer(modifier = Modifier.weight(1f))
            OnboardingTypo(onboardingData)
            Spacer(modifier = Modifier.weight(1f))
            CustomButton(
                label = "Get Started",
                onClick = onNavigateToHome
            )
            Spacer(modifier = Modifier.height(50.dp))
        }
    }
}

@Composable
fun OnboardingImages(image: Int, modifier: Modifier = Modifier) {
    Image(
        painter = painterResource(image),
        contentDescription = "Onboarding",
        modifier = modifier.fillMaxWidth(),
        contentScale = ContentScale.FillWidth
    )
}

@Composable
fun OnboardingTypo(onboardingData: OnboardingData) {
    val headline = buildAnnotatedString {
        withStyle(
            SpanStyle(
                fontFamily = ManropeFontFamily,
                fontWeight = FontWeight.W800,
                fontSize = 32.sp
            ),
        ) {
            append(onboardingData.header)
        }
        append("   ")
        withStyle(
            SpanStyle(
                fontFamily = ManropeFontFamily,
                fontWeight = FontWeight.W400,
                fontSize = 32.sp
            ),
        ) {
            append(onboardingData.title)
        }
    }

    return Column(modifier = Modifier.padding(horizontal = 24.dp)) {
        Text(headline, lineHeight = 44.sp)
        Spacer(Modifier.height(16.dp))
        Text(
            onboardingData.desc,
            style = Typography.labelLarge,
            color = MaterialTheme.colorScheme.tertiary
        )
    }
}

@Composable
fun CustomButton(label: String, onClick: () -> Unit, isBordered: Boolean = false) {
    val containerColor =
        if (isBordered) Color.Transparent else MaterialTheme.colorScheme.primary
    val contentColor = MaterialTheme.colorScheme.onPrimary
    val border = BorderStroke(1.dp, color = MaterialTheme.colorScheme.primary)

    Button(
        onClick = onClick,
        border = if (isBordered) border else null,
        colors = ButtonColors(
            containerColor, contentColor, Color.LightGray, Color.Gray
        ),
        modifier = Modifier
            .fillMaxWidth()
            .height(48.dp)
            .padding(horizontal = 24.dp),
        shape = RoundedCornerShape(12.dp)
    ) {
        Text(label, style = Typography.titleMedium)
    }
}