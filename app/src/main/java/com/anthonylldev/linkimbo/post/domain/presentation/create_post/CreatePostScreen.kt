package com.anthonylldev.linkimbo.post.domain.presentation.create_post

import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Check
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.anthonylldev.linkimbo.R
import com.anthonylldev.linkimbo.util.ui.components.CustomTextField
import com.anthonylldev.linkimbo.util.ui.components.StandarToolbar
import com.anthonylldev.linkimbo.util.ui.theme.HintGray
import com.anthonylldev.linkimbo.util.ui.theme.SpaceMedium
import com.anthonylldev.linkimbo.util.ui.presentation.TextFieldState
import kotlinx.coroutines.launch

@Composable
fun CreatePostScreen(
    navController: NavController,
    createPostViewModel: CreatePostViewModel = hiltViewModel()
) {

    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) {
        createPostViewModel.setImageUriState(it)
    }

    Column(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        StandarToolbar(
            navController = navController,
            modifier = Modifier.fillMaxWidth(),
            title = stringResource(id = R.string.create_post),
            showBackArrow = true,
            navActions = {
                Icon(
                    imageVector = Icons.Default.Check,
                    contentDescription = "",
                    tint = HintGray,
                    modifier = Modifier
                        .padding(SpaceMedium)
                        .clickable {
                             createPostViewModel.saveImage()
                        }
                )
            }
        )

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(SpaceMedium)
        ) {
            Box(
                modifier = Modifier
                    .aspectRatio(16f / 9f)
                    .fillMaxWidth()
                    .border(
                        width = 1.dp,
                        color = MaterialTheme.colors.onBackground,
                        shape = MaterialTheme.shapes.medium
                    )
                    .clickable {
                        launcher.launch("image/*")
                    },
                contentAlignment = Alignment.Center
            ) {
                if (createPostViewModel.bitmap.value == null) {
                    Icon(
                        imageVector = Icons.Default.Add,
                        contentDescription = stringResource(id = R.string.create_post),
                        tint = MaterialTheme.colors.onBackground
                    )
                } else {
                    Image(
                        bitmap = createPostViewModel.bitmap.value!!.asImageBitmap(),
                        contentDescription = null)
                }
            }

            Spacer(modifier = Modifier.height(SpaceMedium))

            CustomTextField(
                text = createPostViewModel.postDetail.value.text,
                hint = stringResource(id = R.string.post_detail),
                error = createPostViewModel.postDetail.value.error,
                onValueChange = {
                    createPostViewModel.setPostDetailState(TextFieldState(it))
                },
                keyboardActions = KeyboardActions(
                    onDone = {
                         createPostViewModel.saveImage()
                    }
                ),
                imeAction = ImeAction.Done
            )
        }
    }
}