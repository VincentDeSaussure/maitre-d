package maitre.d

import org.junit.Test
import java.time.LocalTime
import kotlin.test.assertEquals

internal class HauteCuisine {
    val seatingDuration: Long = 2
    @Test
    fun candidateSeatsOfOneForASeparateTableOfThreeAlReadyReserveByTwo() {
        val hauteCuisine = Restaurant(listOf(ExclusiveTable(3)), seatingDuration)
        val maitreD = MaitreD(hauteCuisine, mutableListOf(Reservation(2, today)))

        assertEquals(
            maitreD.reserves(Reservation(1, today)),
            REJECTED
        )
    }

    @Test
    fun oneReservationLeftOneTableStillAvailableForTheCandidate() {
        val hauteCuisine = Restaurant(listOf(ExclusiveTable(3), ExclusiveTable(3)), seatingDuration)
        val maitreD = MaitreD(hauteCuisine, mutableListOf(Reservation(2, today)))

        assertEquals(
            maitreD.reserves(Reservation(1, today)),
            ACCEPTED
        )
    }

    @Test
    fun availableTablesSumEnoughButWithoutEnoughSeatsEach() {
        val hauteCuisine = Restaurant(listOf(ExclusiveTable(3), ExclusiveTable(3), ExclusiveTable(3)), seatingDuration)
        val maitreD = MaitreD(hauteCuisine, mutableListOf(Reservation(3, today)))

        assertEquals(
            maitreD.reserves(Reservation(6, today)),
            REJECTED
        )
    }

    @Test
    fun efficientReservationByFavorisationLowerSeatsAttribution() {
        val hauteCuisine = Restaurant(listOf(ExclusiveTable(4), ExclusiveTable(2)), seatingDuration)
        val maitreD = MaitreD(hauteCuisine, mutableListOf(Reservation(2, today)))

        assertEquals(
            maitreD.reserves(Reservation(3, today)),
            ACCEPTED
        )
    }
}
