package com.curiouscreature.kotlin.math

import com.badlogic.gdx.math.Matrix4
import com.badlogic.gdx.math.Quaternion as Quat
import com.badlogic.gdx.math.Vector3
import com.badlogic.gdx.utils.GdxNativesLoader
import kotlin.math.abs
import kotlin.test.Test

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
            floatArrayOf(abs(expected.x), abs(expected.y), abs(expected.z), abs(expected.w)),
            floatArrayOf(abs(actual.x), abs(actual.y), abs(actual.z), abs(actual.w))
        )
    }
}
