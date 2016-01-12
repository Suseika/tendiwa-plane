package org.tendiwa.plane.directions

enum class OrdinalDirection(
    override val dx: Int,
    override val dy: Int
) : KingDirection {
    NE(1, -1),
    SE(1, 1),
    SW(-1, 1),
    NW(-1, -1);

    override val cw8: CardinalDirection
        get() = when (this) {
            NE -> CardinalDirection.E
            SE -> CardinalDirection.S
            SW -> CardinalDirection.W
            NW -> CardinalDirection.N
        }
    override val ccw8: CardinalDirection
        get() = when (this) {
            NE -> CardinalDirection.N
            SE -> CardinalDirection.E
            SW -> CardinalDirection.S
            NW -> CardinalDirection.W
        }

    override val cw4: OrdinalDirection
        get() = when (this) {
            NE -> SE
            SE -> SW
            SW -> NW
            NW -> NE
        }

    override val ccw4: KingDirection
        get() = when (this) {
            NE -> NW
            SE -> NE
            SW -> SE
            NW -> SW
        }

    override val opposite: OrdinalDirection
        get() = when (this) {
            NE -> SW
            SE -> NW
            SW -> NE
            NW -> SE
        }
}
