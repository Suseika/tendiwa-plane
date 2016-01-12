package org.tendiwa.plane.directions

import org.tendiwa.math.angles.Angle
import org.tendiwa.math.doubles.distance

/**
 * A direction in two-dimensional space.
 *
 * Can be thought of as a two-dimensional vector without magnitude.
 */
interface Direction {
    val radians: Double

    infix operator fun plus(angle: Angle): Direction =
        RadianDirection(radians + angle.radians)

    infix operator fun minus(angle: Angle): Direction =
        RadianDirection(radians - angle.radians)

    /**
     * Returns a positive counter-clockwise angle between two vectors
     */
    infix fun counterClockwiseAngle(ccw: Direction): Angle =
        if (ccw.radians < this.radians) {
            val denormalizedRadians = ccw.radians + Math.PI * 2
            Angle(denormalizedRadians - this.radians)
        } else {
            Angle(this.radians distance ccw.radians)
        }
}

