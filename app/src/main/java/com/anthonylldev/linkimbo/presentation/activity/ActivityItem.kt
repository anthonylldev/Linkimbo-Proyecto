package com.anthonylldev.linkimbo.presentation.activity

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
import com.anthonylldev.linkimbo.domain.models.Activity
import com.anthonylldev.linkimbo.domain.util.ActivityAction
import com.anthonylldev.linkimbo.presentation.ui.theme.SpaceSmall

@Composable
fun ActivityItem(
    activity: Activity,
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
                .padding(SpaceSmall)
            ,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {

            val actionText = when(activity.actionType) {
                is ActivityAction.LikedPost -> stringResource(id = R.string.liked)
                is ActivityAction.CommentOnPost -> stringResource(id = R.string.commented_on)
            }


            Text(text = buildAnnotatedString {
                withStyle(
                    style = SpanStyle(
                        fontWeight = FontWeight.Bold
                    )
                ) {
                    append(activity.username)
                    append(" ")
                }
                append(actionText)
                append(" your post")

            },
            fontSize = 12.sp)

            Text(
                text = activity.formattedTime,
                textAlign = TextAlign.Right,
                fontSize = 12.sp
            )
        }
    }
}