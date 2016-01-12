package org.tendiwa.plane.directions

import org.tendiwa.plane.orientations.Orientation

enum class CardinalDirection(
    override val dx: Int,
    override val dy: Int
) : KingDirection {

    N(0, -1),
    E(1, 0),
    S(0, 1),
    W(-1, 0);

    val orientation: Orientation
        get() = when (this) {
            N, S -> Orientation.VERTICAL
            W, E -> Orientation.HORIZONTAL
        }

    override val cw8: OrdinalDirection
        get() = when (this) {
            N -> OrdinalDirection.NE
            E -> OrdinalDirection.SE
            S -> OrdinalDirection.SW
            W -> OrdinalDirection.NW
        }

    override val ccw8: OrdinalDirection
        get() = when (this) {
            N -> OrdinalDirection.NW
            E -> OrdinalDirection.NE
            S -> OrdinalDirection.SE
            W -> OrdinalDirection.SW
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
