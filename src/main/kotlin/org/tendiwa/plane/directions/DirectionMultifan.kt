package org.tendiwa.plane.directions

import org.tendiwa.collections.consecutiveCircularPairs


/**
 * A set of directions with gaps.
 */
sealed class DirectionMultifan {

    abstract val monofans: Set<DirectionFan>

    abstract val gaps: DirectionMultifan

    abstract fun contains(direction: Direction): Boolean

}

object FullMultifan : DirectionMultifan() {
    override val monofans: Set<DirectionFan>
        get() {
            throw IllegalAccessException(
                "Full multifan can't be represented as a set of fans"
            )
        }

    override val gaps: DirectionMultifan
        get() = SumDirectionMultifan(listOf())


    override fun contains(direction: Direction): Boolean =
        true

}

class SumDirectionMultifan(
    fans: List<DirectionFan>
) : DirectionMultifan() {

    override val monofans: MutableSet<DirectionFan> = mutableSetOf()

    init {
        fans.forEach { add(it) }
    }

    var isFullCircle: Boolean = false

    override val gaps: DirectionMultifan
        get() {
            return when {
                isFullCircle -> SumDirectionMultifan(listOf())
                monofans.isEmpty() -> FullMultifan
                monofans.size == 1 -> SumDirectionMultifan(
                    listOf(
                        DirectionFan(
                            monofans.first().ccw,
                            monofans.first().cw
                        )
                    )
                )
                else -> SumDirectionMultifan(
                    monofans
                        .toList()
                        .consecutiveCircularPairs()
                        .map {
                            DirectionFan(it.first.ccw, it.second.cw)
                        }
                )
            }
        }

    override fun contains(direction: Direction): Boolean =
        monofans.any { it.contains(direction) }

    private fun add(fan: DirectionFan) {
        var coversCw: DirectionFan? = null
        var coversCcw: DirectionFan? = null
        val coversCompletely = mutableListOf<DirectionFan>()
        monofans.forEach {
            val cw = fan.contains(it.cw)
            val ccw = fan.contains(it.ccw)
            if (cw && ccw) {
                if (it.contains(fan.cw)) {
                    assert(it.contains(fan.ccw))
                    coversCw = it
                    coversCcw = it
                } else {
                    coversCompletely.add(it)
                }
            } else if (cw) {
                assert(coversCw == null)
                coversCw = it
            } else if (ccw) {
                assert(coversCcw == null)
                coversCcw = it
            }
        }
        if (coversCcw != null && coversCw != null) {
            if (coversCw == coversCcw) {
                isFullCircle = true
                monofans.remove(coversCw!!)
            } else {
                monofans.remove(coversCw!!)
                monofans.remove(coversCcw!!)
                monofans.add(
                    DirectionFan(
                        coversCw!!.ccw,
                        coversCcw!!.cw
                    )
                )
            }
        } else if (coversCcw != null) {
            monofans.remove(coversCcw!!)
            monofans.add(
                DirectionFan(
                    coversCcw!!.cw,
                    fan.ccw
                )
            )
        } else if (coversCw != null) {
            monofans.remove(coversCw!!)
            monofans.add(
                DirectionFan(
                    fan.cw,
                    coversCw!!.ccw
                )
            )
        } else {
            monofans.add(fan)
        }
        coversCompletely.forEach {
            monofans.remove(it)
        }
    }

}
