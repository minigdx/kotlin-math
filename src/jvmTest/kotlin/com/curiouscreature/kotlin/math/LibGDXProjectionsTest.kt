package com.curiouscreature.kotlin.math

import com.badlogic.gdx.math.Matrix4
import org.junit.Test

class LibGDXProjectionsTest {

    @Test
    fun orhotgraphic() {
        val libgdx = Matrix4().setToOrtho(
            10f, 20f,
            30f, 40f,
            0f, 10f
        )

        val mat = ortho(10f, 20f, 30f, 40f, 0f, 10f)

        assertEquals(libgdx, mat)
    }
}
