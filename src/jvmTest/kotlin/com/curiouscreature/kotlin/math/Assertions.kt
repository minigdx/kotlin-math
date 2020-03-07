package com.curiouscreature.kotlin.math

import org.junit.Assert

fun assertArrayEquals(expected: FloatArray, actual: FloatArray, delta: Float = 0.0001f) =
    Assert.assertArrayEquals(expected, actual, delta)
