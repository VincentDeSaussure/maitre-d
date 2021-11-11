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
    fun oneReservationLeftOneTableStillAvailableForTheCandidate() {
        val hauteCuisine = Restaurant(listOf(ExclusiveTable(3), ExclusiveTable(3)))
        val maitreD = MaitreD(hauteCuisine, mutableListOf(Reservation(2, today)))

        assertEquals(
            maitreD.reserves(CanditateReservation(1, today)),
            ACCEPTED
        )
    }

    @Test
    fun availableTablesSumEnoughButWithoutEnoughSeatsEach() {
        val hauteCuisine = Restaurant(listOf(ExclusiveTable(3), ExclusiveTable(3), ExclusiveTable(3)))
        val maitreD = MaitreD(hauteCuisine, mutableListOf(Reservation(3, today)))

        assertEquals(
            maitreD.reserves(CanditateReservation(6, today)),
            REJECTED
        )
    }

    @Test
    fun efficientReservationByFavorisationLowerSeatsAttribution() {
        val hauteCuisine = Restaurant(listOf(ExclusiveTable(4), ExclusiveTable(2)))
        val maitreD = MaitreD(hauteCuisine, mutableListOf(Reservation(2, today)))

        assertEquals(
            maitreD.reserves(CanditateReservation(3, today)),
            ACCEPTED
        )
    }
}
