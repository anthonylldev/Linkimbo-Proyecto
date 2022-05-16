package com.anthonylldev.linkimbo.presentation.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.sp
import com.anthonylldev.linkimbo.R

@Composable
fun AuthTermsConditionsAndPolicy(
    modifier: Modifier = Modifier
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .fillMaxWidth()
    ) {

        Text(
            text = stringResource(id = R.string.auth_message),
            style = TextStyle(
                color = MaterialTheme.colors.onBackground,
                fontSize = 14.sp
            )
        )

        Row {
            ClickableText(
                text = buildAnnotatedString {
                    withStyle(
                        style = SpanStyle(
                            color = MaterialTheme.colors.primary,
                            fontSize = 14.sp
                        )
                    ) {
                        append(stringResource(id = R.string.terms_conditions))
                    }
                }
            ) {
                // TODO
            }

            Text(
                text = " " + stringResource(id = R.string.and) + " ",
                style = TextStyle(
                    fontSize = 14.sp
                )
            )

            ClickableText(
                text = buildAnnotatedString {
                    withStyle(
                        style = SpanStyle(
                            color = MaterialTheme.colors.primary,
                            fontSize = 14.sp
                        )
                    ) {
                        append(stringResource(id = R.string.privacy_policy))
                    }
                }
            ) {
                // TODO
            }
        }
    }
}