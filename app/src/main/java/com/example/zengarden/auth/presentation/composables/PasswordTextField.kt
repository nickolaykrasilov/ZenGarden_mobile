package com.example.zengarden.auth.presentation.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lint.kotlin.metadata.Visibility
import com.example.zengarden.auth.presentation.AuthEvent
import com.example.zengarden.ui.theme.ZenGardenTheme
import kotlin.math.sin

@Composable
fun PasswordTextField(
    text: String,
    onValueChange: (String) -> Unit,
    enabled: Boolean = true,
    placeholder: String? = null,
    modifier: Modifier = Modifier,
) {

    var passwordVisible by remember {
        mutableStateOf(false)
    }

    if (placeholder != null) {
        Text(
            text = placeholder,
            color = ZenGardenTheme.colors.onPrimary,
            style = ZenGardenTheme.typography.label,
            modifier = Modifier
                .padding(vertical = 0.dp, horizontal = 20.dp)
        )
    }

    OutlinedTextField(
        value = text,
        onValueChange = { onValueChange(it) },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
        visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
        colors = TextFieldDefaults.colors(
            unfocusedPlaceholderColor = ZenGardenTheme.colors.onPrimary,
            unfocusedIndicatorColor = ZenGardenTheme.colors.primary,
            unfocusedContainerColor = ZenGardenTheme.colors.primary,
            focusedPlaceholderColor = ZenGardenTheme.colors.onPrimary,
            focusedIndicatorColor = ZenGardenTheme.colors.onPrimary,
            focusedContainerColor = ZenGardenTheme.colors.primary,
            focusedTextColor = ZenGardenTheme.colors.onPrimary,
            unfocusedTextColor = ZenGardenTheme.colors.onPrimary,
            focusedTrailingIconColor = ZenGardenTheme.colors.onPrimary,
            unfocusedTrailingIconColor = ZenGardenTheme.colors.onPrimary,
            cursorColor = ZenGardenTheme.colors.onPrimary
        ),
        shape = CircleShape,
        singleLine = true,
        textStyle = ZenGardenTheme.typography.bodySmall,
        enabled = enabled,
        leadingIcon = {
            val image = Icons.Filled.Lock
            Icon(
                image,
                "lock",
                tint = ZenGardenTheme.colors.onPrimary
            )
        },
        trailingIcon = {
            val image = if (passwordVisible)
                Icons.Filled.Visibility
            else Icons.Filled.VisibilityOff

            val description = if (passwordVisible) "Hide password" else "Show password"

            IconButton(onClick = {passwordVisible = !passwordVisible}){
                Icon(imageVector  = image, description)
            }
        },
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun PasswordTextFieldPreview() {
    ZenGardenTheme {

        var text by remember { mutableStateOf("adfasdfa") }

        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
                .background(ZenGardenTheme.colors.background)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth(0.7f)
            ) {

                PasswordTextField(
                    text = text,
                    onValueChange = { text = it },
                    placeholder = "Password",
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(10.dp))
                PasswordTextField(
                    text = "",
                    placeholder = "Confirm password",
                    onValueChange = { text = it },
                    modifier = Modifier.fillMaxWidth()
                )
            }

        }
    }

}