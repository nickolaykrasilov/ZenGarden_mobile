package com.example.zengarden.auth.presentation.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.zengarden.R
import com.example.zengarden.auth.presentation.AuthEvent
import com.example.zengarden.auth.presentation.AuthState
import com.example.zengarden.ui.theme.ZenGardenTheme


@Composable
fun SignUpScreen(
    paddingValues: PaddingValues,
    state: AuthState.SignUpState,
    onEvent: (AuthEvent) -> Unit,
    modifier: Modifier
) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .padding(paddingValues)
            .then(modifier)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth(0.8f)
        ) {

            Text(
                text = stringResource(R.string.sign_up) + " " + stringResource(R.string.seedling_emj),
                color = ZenGardenTheme.colors.onAccent,
                style = ZenGardenTheme.typography.title,
                modifier = Modifier
                    .padding(start = 10.dp)
            )

            Spacer(Modifier.height(20.dp))

            UsernameTextField(
                text = state.username,
                onValueChange = { onEvent(AuthEvent.OnSignUpUsernameChanged(it)) },
                placeholder = "Username",
                modifier = Modifier
                    .fillMaxWidth()
            )

            Spacer(Modifier.height(10.dp))

            PasswordTextField(
                text = state.password,
                onValueChange = { onEvent(AuthEvent.OnSignUpPasswordChanged(it)) },
                placeholder = "Password",
                modifier = Modifier
                    .fillMaxWidth()
            )

            Spacer(Modifier.height(10.dp))

            PasswordTextField(
                text = state.confirmPassword,
                onValueChange = { onEvent(AuthEvent.OnSignUpConfirmPasswordChanged(it)) },
                placeholder = "Confirm password",
                modifier = Modifier
                    .fillMaxWidth()
            )

            Box(
                contentAlignment = Alignment.BottomStart,
                modifier = Modifier.height(30.dp)
            ) {
                if (state.error != null) {
                    Text(
                        text = state.error,
                        color = ZenGardenTheme.colors.error,
                        style = ZenGardenTheme.typography.bodySmall,
                        modifier = Modifier
                            .padding(start = 20.dp)
                    )
                }
            }



            ActionButton(
                onClick = { onEvent(AuthEvent.SubmitSignUp) },
                text = stringResource(R.string.sign_up),
                isLoading = state.isLoading,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(55.dp)
            )

            Spacer(Modifier.height(10.dp))

            Text(
                text = stringResource(R.string.to_sign_in),
                color = ZenGardenTheme.colors.onAccent,
                style = ZenGardenTheme.typography.label,
                modifier = Modifier
                    .padding(start = 20.dp)
                    .clickable {
                        onEvent(AuthEvent.SwitchToSignIn)
                    }
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SignUpScreenPreview() {
    ZenGardenTheme {
        SignUpScreen(
            paddingValues = PaddingValues(0.dp),
            state = AuthState.SignUpState(),
            onEvent = {},
            modifier = Modifier
                .fillMaxSize()
                .background(ZenGardenTheme.colors.background)
        )
    }
}