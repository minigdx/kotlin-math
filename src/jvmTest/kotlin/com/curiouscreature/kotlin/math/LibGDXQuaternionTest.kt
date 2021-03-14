package com.curiouscreature.kotlin.math

import com.badlogic.gdx.math.Matrix4
import com.badlogic.gdx.math.Vector3
import com.badlogic.gdx.utils.GdxNativesLoader
import kotlin.test.Test
import com.badlogic.gdx.math.Quaternion as Quat

class LibGDXQuaternionTest {
    init {
        GdxNativesLoader.load()
    }

    @Test
    fun fromMatrix() {
        val expected = Quat().apply {
            val matrix = Matrix4().rotate(Vector3(1f, 1f, 1f), 25f)
            setFromMatrix(matrix)
        }

        val matrix = Mat4.identity() * rotation(normalize(Float3(1f, 1f, 1f)), 25f)
        val actual = Quaternion.from(matrix)
        assertEquals(expected, actual)
    }

    @Test
    fun interpolate() {
        val fromLibgdx = Quat(0f, 0f, 0f, 1f)
        val toLibgdx = Quat(Vector3(1f, 0f, 0.5f), 25f)

        val from = Quaternion(0f, 0f, 0f, 1f)
        val matrix = Mat4.identity() * rotation(normalize(Float3(1f, 0f, 0.5f)), 25f)
        val to = Quaternion.from(matrix)

        val result = interpolate(from, to, 0.5f)
        val resultLibgdx = fromLibgdx.slerp(toLibgdx, 0.5f)

        assertEquals(resultLibgdx, result)
    }

    fun assertEquals(expected: Quat, actual: Quaternion) {
        assertArrayEquals(
            floatArrayOf(expected.x, expected.y, expected.z, expected.w),
            floatArrayOf(actual.x, actual.y, actual.z, actual.w)
        )
    }
}
