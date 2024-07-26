package com.example.numbersfactstesttask.presentation.get_fact

import androidx.activity.compose.setContent
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextReplacement
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.numbersfactstesttask.core.MainActivity
import com.example.numbersfactstesttask.core.MainScreenRoutes
import com.example.numbersfactstesttask.core.di.AppModule
import com.example.numbersfactstesttask.core.di.RepositoryModule
import com.example.numbersfactstesttask.core.ui.theme.NumbersFactsTestTaskTheme
import com.example.numbersfactstesttask.core.util.TestTags
import com.example.numbersfactstesttask.data.local.NumbersDao
import com.example.numbersfactstesttask.data.remote.NumbersApi
import com.example.numbersfactstesttask.domain.repository.NumbersRepository
import com.example.numbersfactstesttask.domain.use_case.GetFactUseCase
import com.example.numbersfactstesttask.domain.use_case.GetFactsHistoryUseCase
import com.example.numbersfactstesttask.domain.use_case.GetRandomFactUseCase
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.UninstallModules
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import javax.inject.Inject

@HiltAndroidTest
@UninstallModules(AppModule::class, RepositoryModule::class)
class GetFactScreenTest {

    @get:Rule(order = 0)
    val hiltRule = HiltAndroidRule(this)

    @get:Rule(order = 1)
    val composeRule = createAndroidComposeRule<MainActivity>()

    @Inject
    lateinit var numbersApi: NumbersApi

    @Inject
    lateinit var numbersDao: NumbersDao

    @Inject
    lateinit var numbersRepository: NumbersRepository

    @Inject
    lateinit var getFactUseCase: GetFactUseCase

    @Inject
    lateinit var getRandomFactUseCase: GetRandomFactUseCase

    @Inject
    lateinit var getFactsHistoryUseCase: GetFactsHistoryUseCase

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
                }
            }
        }
    }

    @Test
    fun clickGetFact_withoutInternet_errorMessageIsVisible() {
        composeRule.onNodeWithTag(TestTags.ERROR_MESSAGE).assertDoesNotExist()
        composeRule.onNodeWithText("Enter your number").performTextReplacement("9")
        composeRule.onNodeWithText("Get fact").performClick()
        composeRule.onNodeWithTag(TestTags.ERROR_MESSAGE).assertIsDisplayed()
    }

    @Test
    fun clickGetFact_withInternet_errorMessageIsNotVisible() {
        composeRule.onNodeWithTag(TestTags.ERROR_MESSAGE).assertDoesNotExist()
        composeRule.onNodeWithText("Enter your number").performTextReplacement("9")
        composeRule.onNodeWithText("Get fact").performClick()
        composeRule.onNodeWithTag(TestTags.ERROR_MESSAGE).assertDoesNotExist()
    }

    @Test
    fun clickGetRandomFact_withoutInternet_errorMessageIsVisible() {
        composeRule.onNodeWithTag(TestTags.ERROR_MESSAGE).assertDoesNotExist()
        composeRule.onNodeWithText("Get fact about random number").performClick()
        composeRule.onNodeWithTag(TestTags.ERROR_MESSAGE).assertIsDisplayed()
    }

    @Test
    fun clickGetRandomFact_withInternet_errorMessageIsNotVisible() {
        composeRule.onNodeWithTag(TestTags.ERROR_MESSAGE).assertDoesNotExist()
        composeRule.onNodeWithText("Get fact about random number").performClick()
        composeRule.onNodeWithTag(TestTags.ERROR_MESSAGE).assertDoesNotExist()
    }
}