package com.example.numbersfactstesttask.presentation

import androidx.activity.compose.setContent
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.test.isDisplayed
import androidx.compose.ui.test.isNotDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onChild
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextReplacement
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.example.numbersfactstesttask.core.MainActivity
import com.example.numbersfactstesttask.core.MainScreenRoutes
import com.example.numbersfactstesttask.core.di.AppModule
import com.example.numbersfactstesttask.core.di.RepositoryModule
import com.example.numbersfactstesttask.core.ui.theme.NumbersFactsTestTaskTheme
import com.example.numbersfactstesttask.core.util.TestTags
import com.example.numbersfactstesttask.presentation.get_fact.GetFactScreen
import com.example.numbersfactstesttask.presentation.get_fact.GetFactViewModel
import com.example.numbersfactstesttask.presentation.show_full_fact.ShowFullFactScreen
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.UninstallModules
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@HiltAndroidTest
@UninstallModules(AppModule::class, RepositoryModule::class)
class GetFactScreenTest {

    @get:Rule(order = 0)
    val hiltRule = HiltAndroidRule(this)

    @get:Rule(order = 1)
    val composeRule = createAndroidComposeRule<MainActivity>()

    @Before
    fun setUp() {
        hiltRule.inject()
        composeRule.activity.setContent {
            NumbersFactsTestTaskTheme {
                val viewModel = hiltViewModel<GetFactViewModel>()
                val state by viewModel.state.collectAsState()
                val navController = rememberNavController()
                NavHost(
                    navController = navController,
                    startDestination = MainScreenRoutes.GetFactScreen
                ) {
                    composable<MainScreenRoutes.GetFactScreen> {
                        GetFactScreen(
                            navController = navController,
                            state = state,
                            onEvent = viewModel::onEvent
                        )
                    }
                    composable<MainScreenRoutes.ShowFullFactScreen> {
                        val numberFact = it.toRoute<MainScreenRoutes.ShowFullFactScreen>()
                        ShowFullFactScreen(
                            navController = navController,
                            number = numberFact.number,
                            factText = numberFact.factText
                        )
                    }
                }
            }
        }
    }

    @Test
    fun clickOnFact_navToShowFullFactScreen_factIsVisible_navBack() {
        composeRule.onNodeWithText("Enter your number").performTextReplacement("9")
        composeRule.onNodeWithText("Get fact").performClick()
        composeRule.waitUntil {
            composeRule.onNodeWithTag(TestTags.LOADING).isDisplayed()
            composeRule.onNodeWithTag(TestTags.LOADING).isNotDisplayed()
        }
        composeRule.onNodeWithTag(TestTags.FACT_LAZY_COLUMN)
            .onChild()
            .performClick()

        composeRule.onNodeWithText(TestTags.FACT_NUMBER).isDisplayed()
        composeRule.onNodeWithText(TestTags.FACT_TEXT).isDisplayed()
        composeRule.onNodeWithContentDescription("Get back").performClick()

        composeRule.onNodeWithText(TestTags.FACT_NUMBER).assertDoesNotExist()
        composeRule.onNodeWithText(TestTags.FACT_TEXT).assertDoesNotExist()
        composeRule.onNodeWithTag(TestTags.FACT_LAZY_COLUMN)
            .onChild()
            .isDisplayed()
    }
}