package com.curiouscreature.kotlin.math

import kotlin.math.abs
import kotlin.test.fail

fun assertEquals(
    expected: Float,
    actual: Float,
    delta: Float = 0.000001f,
    message: String = "$expected != $actual (𝝙 $delta)"
) {
    if (abs(expected - actual) > delta) {
        fail(message)
    }
}

fun assertArrayEquals(expected: FloatArray, actual: FloatArray, delta: Float = 0.001f) {
    expected.zip(actual).forEach {
        assertEquals(it.first, it.second, delta)
    }
}

fun assertArrayEquals(expected: Array<Float>, actual: Array<Float>, delta: Float = 0.001f) {
    assertArrayEquals(expected.toFloatArray(), actual.toFloatArray(), delta)
}
