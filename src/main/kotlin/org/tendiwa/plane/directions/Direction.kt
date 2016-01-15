package org.tendiwa.plane.directions

import org.tendiwa.math.angles.AngularMeasure
import org.tendiwa.plane.directions.OrdinalDirection.*
import org.tendiwa.tools.butIf
import java.lang.Math.PI

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
        AngularMeasure(
            ccw.greaterAlternativeRadians(this) - radians
        )
}

fun Direction.greaterAlternativeRadians(cw: Direction): Double =
    this.radians
        .butIf(
            { cw.radians > this.radians },
            { this.radians + PI * 2 }
        )

val Direction.quarter: OrdinalDirection?
    // TODO: Can be optimized
    get() = if (radians > 0 && radians < Math.PI / 2) {
        NE
    } else if (radians > Math.PI / 2 && radians < Math.PI) {
        NW
    } else if (radians > Math.PI && radians < Math.PI / 2 * 3) {
        SW
    } else if (radians > Math.PI / 2 * 3 && radians < Math.PI * 2) {
        SE
    } else {
        null
    }

fun Direction.reverse(): Direction = RadianDirection(radians + PI)
