package org.tendiwa.plane.directions

import org.junit.Test
import kotlin.test.assertEquals

class DirectionMultifanTest {

    @Test
    fun `merges monofans`() {
        val multifan = SumDirectionMultifan(
            listOf(
                DirectionFan(
                    OrdinalDirection.NE,
                    OrdinalDirection.NW
                ),
                DirectionFan(
                    OrdinalDirection.NW,
                    CardinalDirection.S
                ),
                DirectionFan(
                    OrdinalDirection.SE,
                    CardinalDirection.E
                )
            )
        )
        assertEquals(
            2,
            multifan.monofans.size
        )
        assert(
            multifan.contains(
                RadianDirection(CardinalDirection.E.radians - 0.01)
            )
        )
        assert(
            !multifan.contains(
                RadianDirection(CardinalDirection.E.radians + 0.01)
            )
        )
        assert(
            multifan.contains(
                OrdinalDirection.NW
            )
        )
    }

    @Test
    fun `computes gaps`() {
        val multifan = SumDirectionMultifan(
            listOf(
                DirectionFan(
                    OrdinalDirection.NE,
                    OrdinalDirection.NW
                ),
                DirectionFan(
                    OrdinalDirection.NW,
                    CardinalDirection.S
                ),
                DirectionFan(
                    OrdinalDirection.SE,
                    CardinalDirection.E
                )
            )
        )
            .gaps
        assertEquals(
            2,
            multifan.monofans.size
        )
        assert(
            !multifan.contains(
                RadianDirection(CardinalDirection.E.radians - 0.01)
            )
        )
        assert(
            multifan.contains(
                RadianDirection(CardinalDirection.E.radians + 0.01)
            )
        )
        assert(
            !multifan.contains(
                OrdinalDirection.NW
            )
        )
    }

    @Test
    fun `can merge into full multifan`() {
        SumDirectionMultifan(
            listOf(
                DirectionFan(
                    CardinalDirection.E,
                    CardinalDirection.N
                ),
                DirectionFan(
                    CardinalDirection.W,
                    CardinalDirection.S
                ),
                DirectionFan(
                    CardinalDirection.N,
                    CardinalDirection.W
                ),
                DirectionFan(
                    CardinalDirection.S,
                    CardinalDirection.E
                )
            )
        )
            .apply {
                assert(isFullCircle)
            }
            .gaps
            .apply {
                assertEquals(0, monofans.size)
            }
    }
}
