package com.anthonylldev.linkimbo.activity.domain.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.anthonylldev.linkimbo.R
import com.anthonylldev.linkimbo.activity.application.data.ActivityResponse
import com.anthonylldev.linkimbo.util.Constants
import com.anthonylldev.linkimbo.util.DateFormatUtil
import com.anthonylldev.linkimbo.util.ui.theme.SpaceSmall

@Composable
fun ActivityItem(
    activityResponse: ActivityResponse,
    modifier: Modifier = Modifier
) {
    Card(
        shape = MaterialTheme.shapes.medium,
        backgroundColor = Color.LightGray,
        elevation = 5.dp
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(SpaceSmall),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {

            val actionText: String = when (activityResponse.actionType) {
                "liked_post" -> {
                    stringResource(id = R.string.liked)
                }
                "liked_comment" -> {
                    stringResource(id = R.string.liked)
                }
                "comment_post" -> {
                    stringResource(id = R.string.commented_on)
                }
                else -> {
                    ""
                }
            }


            Text(
                text = buildAnnotatedString {
                    withStyle(
                        style = SpanStyle(
                            fontWeight = FontWeight.Bold
                        )
                    ) {
                        append(activityResponse.userName)
                        append(" ")
                    }
                    append(actionText)


                    when (activityResponse.actionType) {
                        "liked_post" -> {
                            append(" your post")
                        }
                        "liked_comment" -> {
                            append(" your comment")
                        }
                        "comment_post" -> {
                            append(" your post")
                        }
                    }
                },
                fontSize = 12.sp
            )

            Text(
                text = DateFormatUtil.timestampToFormattedString(
                    timestamp = activityResponse.timestamp,
                    patter = Constants.PATTER_TIME
                ),
                textAlign = TextAlign.Right,
                fontSize = 12.sp
            )
        }
    }
}