package org.tendiwa.plane.directions

import org.junit.Assert.assertEquals
import org.junit.Test
import org.tendiwa.math.angles.AngularMeasure
import org.tendiwa.math.constants.EPSILON
import org.tendiwa.plane.directions.CardinalDirection.E
import org.tendiwa.plane.directions.OrdinalDirection.*

class DirectionTest {
    @Test
    fun `rotates by angle by adding`() {
        assertEquals(
            RadianDirection(2.0),
            RadianDirection(1.0) + AngularMeasure(1.0)
        )
    }

    @Test
    fun `rotates by angle by subtracting`() {
        assertEquals(
            RadianDirection(0.5),
            RadianDirection(1.0) - AngularMeasure(0.5)
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
            AngularMeasure(1.0),
            RadianDirection(0.0) counterClockwiseAngle RadianDirection(1.0)
        )
    }

    @Test
    fun `counter clockwise angle can span over 0 direction`() {
        assertEquals(
            AngularMeasure.RIGHT,
            RadianDirection(Math.PI / 4 * 7)
                .counterClockwiseAngle(RadianDirection(Math.PI / 4))
        )
    }

    @Test
    fun `sun returns specified number of rays`() {
        E
            .sun(9)
            .apply { assertEquals(9, size) }
    }

    @Test
    fun `rays of sun are regular`() {
        E
            .sun(9)
            .map { it.radians }
            .sum()
            .apply { assert(this > 0.0) }
            .let { it % Math.PI * 2 }
            .apply { assertEquals(0.0, this, EPSILON) }
    }

    @Test
    fun `ordinal directions are in corresponding quarters`() {
        assertEquals(NE, NE.quarter)
        assertEquals(SE, SE.quarter)
        assertEquals(SW, SW.quarter)
        assertEquals(NW, NW.quarter)
    }
}
