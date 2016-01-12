package org.tendiwa.plane.directions

import org.tendiwa.plane.directions.OrdinalDirection.*
import org.tendiwa.plane.orientations.Orientation
import org.tendiwa.plane.orientations.Orientation.HORIZONTAL
import org.tendiwa.plane.orientations.Orientation.VERTICAL

enum class CardinalDirection(
    override val dx: Int,
    override val dy: Int,
    override val radians: Double
) : KingDirection {

    N(0, -1, Math.PI / 2 * 3),
    E(1, 0, 0.0),
    S(0, 1, Math.PI / 2),
    W(-1, 0, Math.PI);

    val orientation: Orientation
        get() = when (this) {
            N, S -> VERTICAL
            W, E -> HORIZONTAL
        }

    override val cw8: OrdinalDirection
        get() = when (this) {
            N -> NE
            E -> SE
            S -> SW
            W -> NW
        }

    override val ccw8: OrdinalDirection
        get() = when (this) {
            N -> NW
            E -> NE
            S -> SE
            W -> SW
        }

    override val cw4: CardinalDirection
        get() = when (this) {
            N -> E
            E -> S
            S -> W
            W -> N
        }

    override val ccw4: CardinalDirection
        get() = when (this) {
            N -> W
            E -> N
            S -> E
            W -> S
        }

    override val opposite: KingDirection
        get() = when (this) {
            N -> S
            E -> W
            S -> N
            W -> E
        }
}
