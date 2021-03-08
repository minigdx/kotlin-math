package com.curiouscreature.kotlin.math

import kotlin.test.Test
import kotlin.test.assertTrue

class QuaternionTest {

    @Test
    fun quaternionIdentity() {
        val quaternion = Quaternion.identity()
        assertEquals(Quaternion(0f, 0f, 0f, 1f), quaternion)
    }

    @Test
    fun quaternionIsIdentity() {
        assertTrue(Quaternion(0f, 0f, 0f, 1f).isIdentity())
    }

    @Test
    fun fromMat4() {
        assertEquals(
            normalize(Quaternion(0f, -0f, 0f, 1f)),
            Quaternion.from(rotation(Float3(0f, 0f, 0f)))
        )

        assertEquals(
            normalize(Quaternion(0.7071068f, 0.0f, 0.0f, 0.7071067f)),
            Quaternion.from(rotation(Float3(1f, 0f, 0f), 90f))
        )

        assertEquals(
            normalize(Quaternion(0.0f, 0.0f, 0.70710677f, 0.70710677f)),
            Quaternion.from(rotation(Float3(0f, 0f, 1f), 90f))
        )

        assertEquals(
            normalize(Quaternion(0.5f, 0.0f, 0.5f, 0.70710677f)),
            Quaternion.from(rotation(normalize(Float3(1f, 0f, 1f)), 90f))
        )
    }

    @Test
    fun fromEulers() {
        val quaternion = Quaternion.fromEulers(1f, 0f, 0f, 90f)
        val matrix = Quaternion.from(rotation(normalize(Float3(1f, 0f, 0f)), 90f))
        assertEquals(matrix, quaternion)
    }

    companion object {
        fun assertEquals(expected: Quaternion, actual: Quaternion) {
            assertEquals(expected.x, actual.x)
            assertEquals(expected.y, actual.y)
            assertEquals(expected.z, actual.z)
            assertEquals(expected.w, actual.w)
        }
    }
}
