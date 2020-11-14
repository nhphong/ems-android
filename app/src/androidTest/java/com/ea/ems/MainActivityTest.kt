package com.ea.ems

import androidx.test.core.app.ActivityScenario
import org.junit.Assert.assertEquals
import org.junit.Test

class MainActivityTest {

    private val activityScenario = ActivityScenario.launch(MainActivity::class.java)

    @Test
    fun sampleTest() {
        assertEquals(2, 1 + 1)
    }
}
