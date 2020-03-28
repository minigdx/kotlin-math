package com.curiouscreature.kotlin.math

import com.badlogic.gdx.math.Interpolation
import com.badlogic.gdx.math.Vector3
import com.badlogic.gdx.utils.GdxNativesLoader
import kotlin.test.Test

class LibGDXFloat3Test {

    init {
        GdxNativesLoader.load()
    }

    @Test
    fun interpolate() {
        val fromLibgdx = Vector3(0f, 0f, 0f)
        val toLibgdx = Vector3(0.25f, 0.5f, 1f)
        val resultLibgdx = fromLibgdx.interpolate(toLibgdx, 0.75f, Interpolation.linear)

        val from = Float3(0f, 0f, 0f)
        val to = Float3(0.25f, 0.5f, 1f)
        val result = interpolate(from, to, 0.75f)

        assertEquals(resultLibgdx.x, result.x)
        assertEquals(resultLibgdx.y, result.y)
        assertEquals(resultLibgdx.z, result.z)
    }
}
