package com.example.weatherapp.navigation

import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.navigation.testing.TestNavHostController
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.weatherapp.views.citieslist.CitiesListFragment
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class CitiesListFragmentTest {

    @Test
    fun testNavigationToLocationDetailsScreen() {

        val navController = TestNavHostController(ApplicationProvider.getApplicationContext())

        val citiesListScenario = launchFragmentInContainer<CitiesListFragment>()

        citiesListScenario.onFragment { fragment ->

        }
    }
}