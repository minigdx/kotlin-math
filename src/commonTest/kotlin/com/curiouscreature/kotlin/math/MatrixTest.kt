/*
 * Copyright (C) 2017 Romain Guy
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.curiouscreature.kotlin.math

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFails

class MatrixTest {

    @Test
    fun Mat3_identity() {
        assertEquals(
            Mat3(
                Float3(1f, 0f, 0f),
                Float3(0f, 1f, 0f),
                Float3(0f, 0f, 1f)
            ),
            Mat3.identity()
        )
    }

    @Test
    fun Mat3_identity_Constructor_fail() {
        assertFails {
            Mat3.fromRowMajor(*8.floatArray())
        }
    }

    @Test
    fun Mat3_ofRowMajor() {
        assertEquals(MAT_3, Mat3.fromRowMajor(*9.floatArray()))
    }

    @Test
    fun Mat4_identity() {
        assertEquals(
            Mat4(
                Float4(1f, 0f, 0f, 0f),
                Float4(0f, 1f, 0f, 0f),
                Float4(0f, 0f, 1f, 0f),
                Float4(0f, 0f, 0f, 1f)
            ),
            Mat4.identity()
        )
    }

    @Test
    fun Mat4_constructor_fails() {
        assertFails {
            Mat4.fromRowMajor(*15.floatArray())
        }
    }

    @Test
    fun Mat_fromRowMajor() {
        assertEquals(MAT_4, Mat4.fromRowMajor(*16.floatArray()))
    }

    @Test
    fun Mat3_transpose() {
        assertEquals(
            Mat3(
                Float3(1f, 2f, 3f),
                Float3(4f, 5f, 6f),
                Float3(7f, 8f, 9f)
            ),
            transpose(MAT_3)
        )
    }

    @Test
    fun Mat3_transpose_identity_is_identity() {
        assertEquals(transpose(Mat3.identity()), Mat3.identity())
    }

    @Test
    fun Mat_inverse() {
        assertEquals(
            Mat3(
                Float3(0f, 1f, 0f),
                Float3(-2f, 1f, 1f),
                Float3(2f, -2f, 0f)
            ),
            inverse(Mat3(
                Float3(1f, 0f, 0.5f),
                Float3(1f, 0f, 0f),
                Float3(1f, 1f, 1f)
            ))
        )
    }

    @Test
    fun Mat3_inverse_identity_is_identity() {
        assertEquals(Mat3.identity(), inverse(Mat3.identity()))
    }

    @Test
    fun scale_Float3() {
        assertEquals(Mat4.identity(), scale(Float3(1f, 1f, 1f)))
    }

    @Test
    fun Mat4_scale() {
        assertEquals(
            Mat4(
                Float4(2f, 0f, 0f, 0f),
                Float4(0f, 4f, 0f, 0f),
                Float4(0f, 0f, 6f, 0f),
                Float4(0f, 0f, 0f, 1f)
            ),
            scale(Mat4(
                Float4(2f, 0f, 0f, 0f),
                Float4(4f, 0f, 0f, 0f),
                Float4(6f, 0f, 0f, 0f),
                Float4(0f, 0f, 0f, 0f)
            ))
        )
    }

    @Test
    fun Mat4_translation_Float3() {
        assertEquals(
            Mat4(
                Float4(1f, 0f, 0f, 0f),
                Float4(0f, 1f, 0f, 0f),
                Float4(0f, 0f, 1f, 0f),
                Float4(1f, 2f, 3f, 1f)
            ),
            translation(Float3(1f, 2f, 3f))
        )
    }

    @Test
    fun Mat4_translation() {
        assertEquals(
            Mat4(
                Float4(1f, 0f, 0f, 0f),
                Float4(0f, 1f, 0f, 0f),
                Float4(0f, 0f, 1f, 0f),
                Float4(4f, 8f, 12f, 1f)
            ),
            translation(MAT_4)
        )
    }

    @Test
    fun Mat4_inverse_identity_is_identity() {
        assertEquals(Mat4.identity(), inverse(Mat4.identity()))
    }

    @Test
    fun Mat4_inverse() {
        assertEquals(
            Mat4(
                Float4(1f, 0f, 0f, 0f),
                Float4(-1f, 1f, 0f, 0f),
                Float4(4f, -4f, 1f, -2f),
                Float4(-2f, 2f, 0f, 1f)

            ),
            inverse(
                Mat4(
                    Float4(1f, 0f, 0f, 0f),
                    Float4(1f, 1f, 0f, 0f),
                    Float4(0f, 0f, 1f, 2f),
                    Float4(0f, -2f, 0f, 1f)
                ))
        )
    }

    @Test
    fun Mat4_non_inversable() {
        // Equality on String because even if the result is correct, the equality fail on iOS
        assertEquals(
            Mat4(
                Float4(Float.POSITIVE_INFINITY, Float.NEGATIVE_INFINITY, Float.NaN, Float.NaN),
                Float4(Float.NEGATIVE_INFINITY, Float.POSITIVE_INFINITY, Float.NaN, Float.NaN),
                Float4(Float.NaN, Float.NaN, Float.NaN, Float.NaN),
                Float4(Float.NaN, Float.NaN, Float.NaN, Float.NaN)

            ).toString(),
            inverse(
                Mat4(
                    Float4(1f, 1f, 0f, 0f),
                    Float4(1f, 1f, 0f, 0f),
                    Float4(0f, 0f, 1f, 2f),
                    Float4(0f, 0f, 0f, 1f)
                )).toString()
        )
    }

    @Test
    fun Mat4_rotation_Float3() {
        assertArrayEquals(
            Mat4(
                Float4(0.998f, 0.0523f, -0.0348f, 0f),
                Float4(-0.0517f, 0.9985f, 0.0174f, 0f),
                Float4(0.0357f, -0.0156f, 0.9992f, 0f),
                Float4(0f, 0f, 0f, 1f)
            ).asGLArray(),
            rotation(Float3(1f, 2f, 3f)).asGLArray()
        )
    }

    @Test
    fun Mat4_rotation() {
        assertArrayEquals(
            Mat4(
                Float4(0.0966f, 0.4833f, 0.87f, 0f),
                Float4(0.169f, 0.507f, 0.8451f, 0f),
                Float4(0.2242f, 0.5232f, 0.8221f, 0f),
                Float4(0f, 0f, 0f, 1f)
            ).asGLArray(),
            rotation(MAT_4).asGLArray()
        )
    }

    @Test
    fun Mat4_rotation_axis() {
        assertArrayEquals(
            Mat4(
                Float4(0.9999f, 5f, 1f, 0f),
                Float4(-1f, 4f, 7f, 0f),
                Float4(5f, 5f, 9f, 0f),
                Float4(0f, 0f, 0f, 1f)
            ).asGLArray(),
            rotation(Float3(1f, 2f, 3f), 90f).asGLArray()
        )
    }

    @Test
    fun Mat4_rotation_by_axis_x() {
        val x = rotation(Float3(1f, 0f, 0f), 90f).rotation.x
        assertEquals(-90f, x, delta = 0.1f)
    }

    @Test
    fun Mat4_rotation_by_axis_y() {
        val y = rotation(Float3(0f, 1f, 0f), 90f).rotation.y
        assertEquals(-90f, y, delta = 0.1f)
    }

    @Test
    fun Mat4_rotation_by_axis_z() {
        val z = rotation(Float3(0f, 0f, 1f), 90f).rotation.z
        assertEquals(-90f, z, delta = 0.1f)
    }

    @Test
    fun normal() {
        assertArrayEquals(
            Mat4(
                Float4(0.0093f, 0.0357f, 0.0502f, 13.0f),
                Float4(0.0186f, 0.0428f, 0.0558f, 14.0f),
                Float4(0.0280f, 0.05f, 0.0614f, 15.0f),
                Float4(0.0373f, 0.0571f, 0.0670f, 16.0f)
            ).asGLArray(),
            normal(MAT_4).asGLArray()
        )
    }

    @Test
    fun lookAt() {
        assertArrayEquals(
            Mat4(
                Float4(0.40824828f, -0.7071067f, -0.5773502588272095f, 0.0f),
                Float4(-0.8164966106414795f, 0f, -0.5773502588272095f, 0.0f),
                Float4(0.40824830532073975f, 0.7071067690849304f, -0.5773502588272095f, 0.0f),
                Float4(-0f, -1.4142135381698608f, 3.464101552963257f, 1.0f)
            ).asGLArray(),
            lookAt(
                eye = Float3(1f, 2f, 3f),
                target = Float3(4f, 5f, 6f),
                up = Float3(7f, 8f, 9f)
            ).asGLArray()
        )
    }

    @Test
    fun lookTowards() {
        assertArrayEquals(
            Mat4(
                Float4(-0.6549f, 0.10792291f, -0.7479144f, 0.0f),
                Float4(-0.34753147f, 0.83584f, 0.42495134f, 0.0f),
                Float4(0.6710031f, 0.5382513f, -0.50994164f, 0.0f),
                Float4(-0.6629832f, -3.3943686f, 1.4278367f, 1.0f)
            ).asGLArray(),
            lookTowards(
                eye = Float3(1f, 2f, 3f),
                direction = Float3(4.4f, -2.5f, 3f),
                up = Float3(3f, 4f, 5f)
            ).asGLArray()
        )
    }

    @Test
    fun perspective() {
        val result = perspective(
            fov = 45f,
            aspect = 1.3333333f,
            far = 100f,
            near = 0.1f
        )

        val expected = Mat4.fromColumnMajor(
            1.8106601238250732f, 0f, 0f, 0f,
            0f, 2.4142136573791504f, 0f, 0f,
            0f, 0f, -1.0020020008087158f, -1f,
            0f, 0f, -0.20020020008087158f, 0f
        )
        assertArrayEquals(expected.asGLArray(), result.asGLArray())
    }

    @Test
    fun ortho() {
        assertArrayEquals(
            Mat4(
                Float4(2.0f, 0.0f, 0.0f, 0.0f),
                Float4(0.0f, 2.0f, 0.0f, 0.0f),
                Float4(0.0f, 0.0f, -2.0f, 0.0f),
                Float4(-3.0f, -7.0f, -11.0f, 1.0f)
            ).asGLArray(),
            ortho(
                l = 1f,
                r = 2f,
                b = 3f,
                t = 4f,
                n = 5f,
                f = 6f
            ).asGLArray()
        )
    }

    @Test
    fun fromQuaternion() {
        val fromQuaternion = Mat4.from(Quaternion(0.5f, 0f, 0.5f, 0.70710677f))
        val fromRotation = rotation(normalize(Float3(1f, 0f, 1f)), 90f)

        assertArrayEquals(fromQuaternion.asGLArray(), fromRotation.asGLArray())
    }

    companion object {
        private val MAT_3 = Mat3(
            Float3(1f, 4f, 7f),
            Float3(2f, 5f, 8f),
            Float3(3f, 6f, 9f)
        )
        private val MAT_4 = Mat4(
            Float4(1f, 5f, 9f, 13f),
            Float4(2f, 6f, 10f, 14f),
            Float4(3f, 7f, 11f, 15f),
            Float4(4f, 8f, 12f, 16f)
        )

        /**
         * @return a FloatArray containing n floats 1f,2f,...,n (float) where n
         * is the @receiver integer.
         */
        private fun Int.floatArray() = FloatArray(this) { (it + 1).toFloat() }
    }
}
