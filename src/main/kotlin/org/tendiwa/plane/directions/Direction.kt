package org.tendiwa.plane.directions

import org.tendiwa.math.doubles.distance

/**
 * A direction in two-dimensional space.
 *
 * Can be thought of as a two-dimensional vector without magnitude.
 */
interface Direction {
    val radians: Double

    infix operator fun plus(angle: org.tendiwa.math.angles.Angle): Direction =
        RadianDirection(radians + angle.radians)

    infix operator fun minus(angle: org.tendiwa.math.angles.Angle): Direction =
        RadianDirection(radians - angle.radians)

    /**
     * Returns a positive angle between two directions.
     */
    infix fun angleBetween(other: Direction): org.tendiwa.math.angles.Angle =
        org.tendiwa.math.angles.Angle(this.radians distance other.radians)
}

