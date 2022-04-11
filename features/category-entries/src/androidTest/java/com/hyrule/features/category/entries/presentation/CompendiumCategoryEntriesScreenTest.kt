package com.hyrule.features.category.entries.presentation

import androidx.navigation.compose.rememberNavController
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.hyrule.features.category.entries.di.domainModule
import com.hyrule.features.category.entries.di.presentationModule
import com.hyrule.features.category.entries.domain.entity.Entry
import com.hyrule.features.category.entries.domain.repository.CategoryEntriesRepository
import com.hyrule.testing.integration.ComposeUITest
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import org.koin.test.KoinTest
import org.koin.test.KoinTestRule

@RunWith(AndroidJUnit4::class)
class CompendiumCategoryEntriesScreenTest : KoinTest,
    ComposeUITest<CompendiumCategoryEntriesRobot, CompendiumCategoryEntriesAssertion>(
        CompendiumCategoryEntriesRobot(), CompendiumCategoryEntriesAssertion()
    ) {

    @get:Rule
    val koinTestRule = KoinTestRule.create {
        androidContext(ApplicationProvider.getApplicationContext())
        modules(mockModules())
    }

    @Test
    fun itShouldShowCompendiumCategoryEntries() {
        returnSuccessFromService()
        showScreen()
        verifyThat {
            assertEntryVisible()
        }
    }

    @Test
    fun itShouldShowCompendiumCategoryEntriesAfterRetry() {
        returnErrorFromService()
        showScreen()

        verifyThat {
            assertErrorVisible()
        } andWithRobot {
            returnSuccessFromService()
            clickOnTryAgain()
        } andVerifyThat {
            assertEntryVisible()
        }
    }

    private fun showScreen() {
        composeTestRule.setContent {
            CompendiumCategoryEntriesScreen(
                navController = rememberNavController(),
                categoryName = "equipment"
            )
        }
    }

    private var returnFromRepository: Result<List<Entry>>? = null

    private fun returnSuccessFromService() {
        returnFromRepository = Result.success(
            listOf(
                Entry(
                    name = "Master Sword",
                    image = "",
                    locations = listOf()
                )
            )
        )
    }

    private fun returnErrorFromService() {
        returnFromRepository = Result.failure(Throwable("error_test"))
    }

    private fun mockModules() = module {
        domainModule()
        presentationModule()
        factory<CategoryEntriesRepository> {
            object : CategoryEntriesRepository {
                override suspend fun getEntries(categoryName: String) = returnFromRepository!!
            }
        }
    }
}
