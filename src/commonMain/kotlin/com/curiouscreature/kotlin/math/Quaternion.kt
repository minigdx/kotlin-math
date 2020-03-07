package com.curiouscreature.kotlin.math

import kotlin.math.sqrt

data class Quaternion(val x: Float, val y: Float, val z: Float, val w: Float) {

    fun isIdentity() = x == 0f && y == 0f && z == 0f && w == 1f

    fun toFloatArray(): FloatArray {
        return floatArrayOf(x, y, z, w)
    }

    override fun toString(): String {
        return "($x $y $z $w)"
    }

    companion object {

        fun identity(): Quaternion {
            return Quaternion(0f, 0f, 0f, 1f)
        }

        // https://www.euclideanspace.com/maths/geometry/rotations/conversions/matrixToQuaternion/
        fun from(m: Mat4): Quaternion {
            val tr = m.x.x + m.y.y + m.z.z

            return if (tr > 0) {
                val s = sqrt(tr + 1f) * 2f // S=4*qw
                val qw = 0.25f * s
                val qx = (m.z.y - m.y.z) / s
                val qy = (m.x.z - m.z.x) / s
                val qz = (m.y.x - m.x.y) / s
                Quaternion(qx, qy, qz, qw)
            } else if ((m.x.x > m.y.y) && (m.x.x > m.z.z)) {
                val s = sqrt(1f + m.x.x - m.y.y - m.z.z) * 2; // S=4*qx
                val qw = (m.z.y - m.y.z) / s
                val qx = 0.25f * s
                val qy = (m.x.y + m.y.x) / s
                val qz = (m.x.z + m.z.x) / s
                Quaternion(qx, qy, qz, qw)
            } else if (m.y.y > m.z.z) {
                val s = sqrt(1.0f + m.y.y - m.x.x - m.z.z) * 2; // S=4*qy
                val qw = (m.x.z - m.z.x) / s
                val qx = (m.x.y + m.y.x) / s
                val qy = 0.25f * s
                val qz = (m.y.z + m.z.y) / s
                Quaternion(qx, qy, qz, qw)
            } else {
                val s = sqrt(1.0f + m.z.z - m.x.x - m.y.y) * 2 // S=4*qz
                val qw = (m.y.x - m.x.y) / s
                val qx = (m.x.z + m.z.x) / s
                val qy = (m.y.z + m.z.y) / s
                val qz = 0.25f * s
                Quaternion(qx, qy, qz, qw)
            }
        }
    }
}

fun normalize(quaternion: Quaternion): Quaternion {
    val (x, y, z, w) = quaternion
    val mag = sqrt(w * w + x * x + y * y + z * z)
    return Quaternion(x / mag, y / mag, z / mag, w / mag)
}

fun interpolate(a: Quaternion, b: Quaternion, blend: Float): Quaternion {
    val dot = a.w * b.w + a.x * b.x + a.y * b.y + a.z * b.z
    val blendI = 1f - blend
    val result = if (dot < 0) {
        val w = blendI * a.w + blend * -b.w
        val x = blendI * a.x + blend * -b.x
        val y = blendI * a.y + blend * -b.y
        val z = blendI * a.z + blend * -b.z
        Quaternion(x, y, z, w)
    } else {
        val w = blendI * a.w + blend * b.w
        val x = blendI * a.x + blend * b.x
        val y = blendI * a.y + blend * b.y
        val z = blendI * a.z + blend * b.z
        Quaternion(x, y, z, w)
    }
    return normalize(result)
}
