package com.revolut.currencies

import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import com.kaspersky.kaspresso.testcases.api.testcase.TestCase
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.Rule

@RunWith(AndroidJUnit4::class)
class SimpleTest : TestCase() {

    @get:Rule
    val activityTestRule = ActivityTestRule(CurrenciesActivity::class.java, true, false)

    @Test
    fun test() =
        run {

            step("Open Currencies Screen") {
                activityTestRule.launchActivity(null)
                CurrenciesScreen {
                    currenciesList {
                        isVisible()
                        hasSize(4)
                    }
                }
            }

            step("Change CADs to USDs") {
                CurrenciesScreen {
                    currenciesList {
                        childAt<CurrencyItem>(3) {
                            this.click()
                        }
                        childAt<CurrencyItem>(0) {
                            this.code.hasText("CAD")
                            this.value.replaceText("1300")
                        }
                        childAt<CurrencyItem>(3) {
                            this.value.containsText("1000")
                        }
                    }
                }
            }

        }
}
