package org.tendiwa.plane.directions

enum class OrdinalDirection(
    override val dx: Int,
    override val dy: Int,
    override val radians: Double
) : KingDirection {
    NE(1, -1, Math.PI / 4),
    SE(1, 1, Math.PI / 4 * 7),
    SW(-1, 1, Math.PI / 4 * 5),
    NW(-1, -1, Math.PI / 4 * 3);

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
