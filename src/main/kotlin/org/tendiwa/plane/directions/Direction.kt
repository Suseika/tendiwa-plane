package org.tendiwa.plane.directions

import org.tendiwa.math.angles.AngularMeasure
import org.tendiwa.math.angles.times
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
    get() = if (radians > 0 && radians < PI / 2) {
        NE
    } else if (radians > PI / 2 && radians < PI) {
        NW
    } else if (radians > PI && radians < PI / 2 * 3) {
        SW
    } else if (radians > PI / 2 * 3 && radians < PI * 2) {
        SE
    } else {
        null
    }

fun Direction.reverse(): Direction = RadianDirection(radians + PI)

/**
 * Returns rays at regular intervals starting from this ray, clockwise.
 */
fun Direction.sun(raysNum: Int): List<Direction> {
    val rayAngle = AngularMeasure(PI * 2 / raysNum)
    return (0 until raysNum)
        .map { i -> this + (rayAngle * i) }
}
