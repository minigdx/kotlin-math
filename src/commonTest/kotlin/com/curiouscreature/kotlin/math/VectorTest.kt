package com.curiouscreature.kotlin.math

import kotlin.test.Test

class VectorTest {

    @Test
    fun Float3_interpolation() {
        val resultA = interpolate(Float3(0f, 1f, 2f), Float3(3f, 4f, 5f), 0f)
        assertEquals(0f, resultA.x)
        assertEquals(1f, resultA.y)
        assertEquals(2f, resultA.z)

        val resultB = interpolate(Float3(0f, 1f, 2f), Float3(3f, 4f, 5f), 1f)
        assertEquals(3f, resultB.x)
        assertEquals(4f, resultB.y)
        assertEquals(5f, resultB.z)
    }
}
