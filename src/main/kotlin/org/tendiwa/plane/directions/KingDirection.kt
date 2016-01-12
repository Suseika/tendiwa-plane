package org.tendiwa.plane.directions

/**
 * Direction in which a chess king can move.
 */
interface KingDirection : Direction {
    val dx: Int
    val dy: Int
    val cw8: KingDirection
    val cw4: KingDirection
    val ccw8: KingDirection
    val ccw4: KingDirection
    val opposite: KingDirection
}
