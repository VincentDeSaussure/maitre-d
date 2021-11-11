package maitre.d

import org.junit.Test
import kotlin.test.assertEquals

internal class HauteCuisine {
    @Test
    fun candidateSeatsOfOneForASeparateTableOfThreeAlReadyReserveByTwo() {
        val hauteCuisine = Restaurant(listOf(ExclusiveTable(3)))
        val maitreD = MaitreD(hauteCuisine, mutableListOf(Reservation(2, today)))

        assertEquals(
            maitreD.reserves(CanditateReservation(1, today)),
            REJECTED
        )
    }

    @Test
    fun candidateSeatsOfOneForTwoSeparateTableOfThreeAlReadyReserveByTwo() {
        val hauteCuisine = Restaurant(listOf(ExclusiveTable(3), ExclusiveTable(3)))
        val maitreD = MaitreD(hauteCuisine, mutableListOf(Reservation(2, today)))

        assertEquals(
            maitreD.reserves(CanditateReservation(1, today)),
            ACCEPTED
        )
    }
}
