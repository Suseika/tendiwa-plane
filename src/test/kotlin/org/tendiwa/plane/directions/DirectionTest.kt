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

}
