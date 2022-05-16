package com.anthonylldev.linkimbo.presentation.auth.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.hilt.navigation.compose.hiltViewModel
import com.anthonylldev.linkimbo.R
import com.anthonylldev.linkimbo.presentation.auth.AuthViewModel
import com.anthonylldev.linkimbo.presentation.auth.screen.util.AuthPages
import com.anthonylldev.linkimbo.presentation.components.AuthTermsConditionsAndPolicy
import com.anthonylldev.linkimbo.presentation.components.CustomButton
import com.anthonylldev.linkimbo.presentation.components.CustomOutlinedButton
import com.google.accompanist.pager.ExperimentalPagerApi
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterialApi::class, ExperimentalPagerApi::class)
@Composable
fun AuthContent(
    coroutineScope: CoroutineScope,
    authViewModel: AuthViewModel = hiltViewModel()
) {

    ConstraintLayout(
        modifier = Modifier
            .fillMaxSize()
    ) {

        val (
            imageConstraint, welcomeConstraint,
            createConstraint, loginConstraint,
            policyConstraint
        ) = createRefs()

        Image(
            painter = painterResource(
                if (isSystemInDarkTheme()) {
                    R.drawable.social_sapce_white
                } else {
                    R.drawable.social_space_black
                }
            ),
            contentDescription = "authImage",
            contentScale = ContentScale.Inside,
            modifier = Modifier
                .height(275.dp)
                .width(275.dp)
                .constrainAs(imageConstraint) {
                    top.linkTo(parent.top, margin = 32.dp)
                    start.linkTo(parent.start, margin = 32.dp)
                    end.linkTo(parent.end, margin = 32.dp)
                }
        )

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxWidth()
                .constrainAs(welcomeConstraint) {
                    top.linkTo(imageConstraint.bottom, margin = 24.dp)
                }
        ) {
            Text(
                text = stringResource(id = R.string.welcome),
                fontWeight = FontWeight.Bold,
                style = TextStyle(
                    color = MaterialTheme.colors.primary,
                    fontSize = 56.sp
                )
            )

            Text(
                text = stringResource(id = R.string.welcome_message),
                fontWeight = FontWeight.Bold,
                style = TextStyle(
                    fontSize = 16.sp
                )
            )

            Text(
                text = stringResource(id = R.string.welcome_message_2),
                fontWeight = FontWeight.Bold,
                style = TextStyle(
                    fontSize = 16.sp
                )
            )
        }

        CustomButton(
            modifier = Modifier
                .constrainAs(createConstraint) {
                    bottom.linkTo(loginConstraint.top, margin = 16.dp)
                    start.linkTo(parent.start, margin = 50.dp)
                    end.linkTo(parent.end, margin = 50.dp)
                    width = Dimension.fillToConstraints
                },
            text = stringResource(id = R.string.create_an_account)
        ) {
            coroutineScope.launch {
                authViewModel.scrollPage(AuthPages.Create)
                authViewModel.sheetAnimateTo(ModalBottomSheetValue.Expanded, 500)
            }

        }

        CustomOutlinedButton(
            modifier = Modifier
                .constrainAs(loginConstraint) {
                    bottom.linkTo(policyConstraint.top, margin = 48.dp)
                    start.linkTo(parent.start, margin = 50.dp)
                    end.linkTo(parent.end, margin = 50.dp)
                    width = Dimension.fillToConstraints
                },
            text = stringResource(id = R.string.log_in)
        ) {
            coroutineScope.launch {
                authViewModel.scrollPage(AuthPages.LogIn)
                authViewModel.sheetAnimateTo(ModalBottomSheetValue.Expanded, 500)
            }
        }

        AuthTermsConditionsAndPolicy(
            modifier = Modifier
                .constrainAs(policyConstraint) {
                    bottom.linkTo(parent.bottom, margin = 16.dp)
                }
        )
    }
}