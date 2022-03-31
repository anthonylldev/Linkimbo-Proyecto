package com.anthonylldev.linkimbo.presentation.auth

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.navigation.NavController
import com.anthonylldev.linkimbo.presentation.auth.components.AuthImage
import com.anthonylldev.linkimbo.presentation.auth.components.AuthTermsConditionsAndPolicity
import com.anthonylldev.linkimbo.presentation.auth.components.AuthWelcome
import com.anthonylldev.linkimbo.presentation.util.components.AuthButton

@Composable
fun AuthScreen(
    navController: NavController
) {
    ConstraintLayout(
        modifier = Modifier
            .fillMaxSize()
    ) {

        val (
            imageConstraint, welcomeConstraint,
            createConstraint, loginConstraint,
            policityConstraint
        ) = createRefs()

        AuthImage(
            modifier = Modifier
                .constrainAs(imageConstraint) {
                    top.linkTo(parent.top, margin = 32.dp)
                    start.linkTo(parent.start, margin = 32.dp)
                    end.linkTo(parent.end, margin = 32.dp)
                }
        )

        AuthWelcome(
            modifier = Modifier
                .constrainAs(welcomeConstraint) {
                    top.linkTo(imageConstraint.bottom, margin = 24.dp)
                }
        )

        AuthButton(
            modifier = Modifier
                .constrainAs(createConstraint) {
                    bottom.linkTo(loginConstraint.top, margin = 16.dp)
                    start.linkTo(parent.start, margin = 50.dp)
                    end.linkTo(parent.end, margin = 46.dp)
                    width = Dimension.fillToConstraints
                },
            text = "Create an account"
        ) {
            // TODO
        }

        AuthButton(
            modifier = Modifier
                .constrainAs(loginConstraint) {
                    bottom.linkTo(policityConstraint.top, margin = 48.dp)
                    start.linkTo(parent.start, margin = 50.dp)
                    end.linkTo(parent.end, margin = 50.dp)
                    width = Dimension.fillToConstraints
                },
            buttonBordered = true,
            text = "Log in"
        ) {
            // TODO
        }

        AuthTermsConditionsAndPolicity(
            modifier = Modifier
                .constrainAs(policityConstraint) {
                    bottom.linkTo(parent.bottom, margin = 16.dp)
                }
        )
    }
}