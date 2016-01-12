package org.tendiwa.plane.directions

import org.tendiwa.math.angles.Angle

/**
 * Direction defined as a counter-clockwise angle from the direction of
 * `Vector(1.0, 0.0)`
 * */
class RadianDirection
/**
 * @param radians Counter-clockwise angle from the direction of
 * `Vector(1.0, 0.0)`
 */
(radians: Double) : Direction {

    override val radians: Double = Angle.normalize(radians)

    init {
        org.tendiwa.tools.argumentConstraint(
            radians,
            { it.isFinite() },
            { "angle must be a finite number" }
        )
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other?.javaClass != javaClass) return false

        other as Direction

        if (radians != other.radians) return false

        return true
    }

    override fun hashCode(): Int =
        radians.hashCode()

    override fun toString(): String =
        "[direction $radians]"
}
