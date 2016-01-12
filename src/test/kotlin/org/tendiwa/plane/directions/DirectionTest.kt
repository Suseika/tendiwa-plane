package org.tendiwa.plane.directions

import org.junit.Test
import org.tendiwa.math.angles.Angle
import kotlin.test.assertEquals

class DirectionTest {
    @Test
    fun `rotates by angle by adding`() {
        assertEquals(
            RadianDirection(2.0),
            RadianDirection(1.0) + Angle(1.0)
        )
    }

    @Test
    fun `rotates by angle by subtracting`() {
        assertEquals(
            RadianDirection(0.5),
            RadianDirection(1.0) - Angle(0.5)
        )
    }

    @Test
    fun `normalizes`() {
        assertEquals(
            RadianDirection(Math.PI / 2 * 3),
            RadianDirection(-Math.PI / 2)
        )
    }

    @Test
    fun `counter clockwise angle`() {
        assertEquals(
            Angle(1.0),
            RadianDirection(0.0) counterClockwiseAngle RadianDirection(1.0)
        )
    }

    @Test
    fun `counter clockwise angle can span over 0 direction`() {
        assertEquals(
            Angle.RIGHT,
            RadianDirection(Math.PI / 4 * 7)
                .counterClockwiseAngle(RadianDirection(Math.PI / 4))
        )
    }
}
