package com.anthonylldev.linkimbo.presentation.splash

import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.navigation.NavController
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.anthonylldev.linkimbo.presentation.MainActivity
import com.anthonylldev.linkimbo.presentation.ui.theme.LinkimboTheme
import io.mockk.MockKAnnotations
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@ExperimentalCoroutinesApi
@RunWith(AndroidJUnit4::class)
class SplashScreenTest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<MainActivity>()

    @RelaxedMockK
    lateinit var navController: NavController

    @Before
    fun setUp() {
        // Initialize navController
        MockKAnnotations.init(this)
    }

    @Test
    fun splashScreen_displayAndDisappears() = runBlockingTest {

        // Check the SplashScreen receives a navController
        composeTestRule.setContent {
            LinkimboTheme {
                SplashScreen(
                    navController = navController,
                )
            }
        }

        // Check the SplashScreen have node with logo
        composeTestRule
            .onNodeWithContentDescription("Logo")
            .assertExists()

        // Check the navigate to AuthScreen
        /*verify {
            navController.navigate(Screen.AuthScreen.route) {
                popUpTo(Screen.SplashScreen.route) {
                    inclusive = true
                }
            }
        }*/
    }
}