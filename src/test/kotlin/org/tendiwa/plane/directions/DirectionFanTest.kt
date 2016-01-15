package org.tendiwa.plane.directions

import org.junit.Assert
import org.junit.Test
import org.tendiwa.math.angles.AngularMeasure
import org.tendiwa.math.constants.EPSILON
import org.tendiwa.plane.directions.CardinalDirection.*
import org.tendiwa.plane.directions.OrdinalDirection.*
import java.lang.Math.PI
import kotlin.test.assertEquals
import kotlin.test.assertFalse

class DirectionFanTest {
    @Test
    fun `angular measure`() {
        DirectionFan(
            RadianDirection(0.0),
            RadianDirection(PI / 2)
        )
            .apply { assertEquals(AngularMeasure.RIGHT, measure) }
    }

    @Test
    fun `bisector`() {
        DirectionFan(cw = E, ccw = N)
            .apply {
                assertEquals(
                    OrdinalDirection.NE.radians,
                    bisector.radians
                )
            }
    }

    @Test
    fun `measure of angle that contains 0 direction`() {
        DirectionFan(cw = SE, ccw = N)
            .apply {
                Assert.assertEquals(
                    AngularMeasure(PI / 4 * 3).radians,
                    measure.radians,
                    EPSILON
                )
            }
    }

    @Test
    fun `angle can contain directions`() {
        DirectionFan(cw = N, ccw = W)
            .apply { assert(contains(NW)) }
    }

    @Test
    fun `direction can be not contained in angle`() {
        DirectionFan(cw = N, ccw = W)
            .apply { assertFalse(contains(NE)) }
    }

    @Test
    fun `transzero angle can contain directions`() {
        DirectionFan(cw = SE, ccw = N)
            .apply { assert(contains(E)) }
    }

    @Test
    fun `direction can be not contained in transzero angle`() {
        DirectionFan(cw = S, ccw = N)
            .apply { assertFalse(contains(W)) }
    }

    @Test
    fun `angle contains directions equal to sides`() {
        DirectionFan(cw = S, ccw = SW)
            .apply { assert(contains(cw) && contains(ccw)) }
    }
}
