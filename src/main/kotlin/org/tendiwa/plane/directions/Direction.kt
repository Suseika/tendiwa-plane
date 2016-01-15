package org.tendiwa.plane.directions

import org.tendiwa.math.angles.AngularMeasure

/**
 * A direction in two-dimensional space.
 *
 * Can be thought of as a two-dimensional vector without magnitude.
 */
interface Direction {
    val radians: Double

    infix operator fun plus(angularMeasure: AngularMeasure): Direction =
        RadianDirection(radians + angularMeasure.radians)

    infix operator fun minus(angularMeasure: AngularMeasure): Direction =
        RadianDirection(radians - angularMeasure.radians)

    /**
     * Returns a positive counter-clockwise angle between two vectors
     */
    infix fun counterClockwiseAngle(ccw: Direction): AngularMeasure =
        if (ccw.radians < this.radians) {
            val denormalizedRadians = ccw.radians + Math.PI * 2
            AngularMeasure(denormalizedRadians - this.radians)
        } else {
            AngularMeasure(ccw.radians - this.radians)
        }
}

