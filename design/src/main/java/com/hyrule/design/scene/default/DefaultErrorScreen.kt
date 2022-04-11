package com.hyrule.design.scene.default

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.hyrule.design.R
import com.hyrule.design.theme.HyruleTheme
import com.hyrule.design.tokens.spacing.Spacing

@Composable
fun DefaultErrorScreen(
    message: String?,
    retry: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .background(MaterialTheme.colors.background)
            .padding(Spacing.medium)
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = message
                .takeIf { !it.isNullOrBlank() }
                ?: stringResource(R.string.connection_error_default_message),
            style = MaterialTheme.typography.h5,
            color = MaterialTheme.colors.onBackground,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = Spacing.medium)
        )

        Button(onClick = retry) {
            Text(
                text = stringResource(R.string.try_again),
                style = MaterialTheme.typography.h6,
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultErrorScreenPreview() {
    HyruleTheme(darkTheme = true) {
        DefaultErrorScreen(null, {})
    }
}
