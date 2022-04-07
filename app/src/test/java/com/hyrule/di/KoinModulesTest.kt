package com.hyrule.di

import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.koin.dsl.koinApplication
import org.koin.test.KoinTest
import org.koin.test.check.checkModules

@RunWith(JUnit4::class)
class KoinModulesTest : KoinTest {

    @Test
    fun verifyKoinModules() {
        koinApplication {
            injectModules()
            checkModules()
        }
    }
}
