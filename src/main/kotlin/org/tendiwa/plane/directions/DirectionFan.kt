package org.tendiwa.plane.directions

import org.tendiwa.math.angles.AngularMeasure
import org.tendiwa.tools.butIf
import java.lang.Math.PI

data class DirectionFan(
    val cw: Direction,
    val ccw: Direction
)

val DirectionFan.measure: AngularMeasure
    get() = cw.counterClockwiseAngle(ccw)

val DirectionFan.bisector: Direction
    get() =
        ccw.radians
            .butIf(
                { it < cw.radians },
                { it + PI * 2 }
            )
            .let { (it + cw.radians) / 2 }
            .let { RadianDirection(it) }

private fun DirectionFan.isTranszero(): Boolean =
    cw.radians > ccw.radians

fun DirectionFan.contains(direction: Direction): Boolean {
    val isTranszero = isTranszero()
    val orderedCcwRadians = ccw.radians
        .butIf(
            { isTranszero },
            { it + PI * 2 }
        )
    val orderedTargetRadians = direction.radians
        .butIf(
            { it < cw.radians },
            { it + PI * 2 }
        )
    return orderedTargetRadians >= cw.radians
        && orderedTargetRadians <= orderedCcwRadians
}
