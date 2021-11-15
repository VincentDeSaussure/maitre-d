package maitre.d

import kotlin.test.Test
import kotlin.test.assertEquals

internal class LaBoutiqueRestaurant {
    val seatingDuration: Long = 2
    val laBoutiqueRestaurant = Restaurant(listOf(CommunalTable(4)), seatingDuration)
    val noReservations = mutableListOf<Reservation>()
    @Test
    fun noExistingReservation() {
        val maitreD = MaitreD(laBoutiqueRestaurant, noReservations)

        assertEquals(ACCEPTED, maitreD.reserves(Reservation(4, today)))
    }

    @Test
    fun noExistingReservationAndTableSizeExceed() {
        val maitreD = MaitreD(laBoutiqueRestaurant, noReservations)

        assertEquals(REJECTED, maitreD.reserves(Reservation(5, today)))
    }

    @Test
    fun existingReservationAtTheCandidateReservationDateCausesAnExceedOfTableSize() {
        val maitreD = MaitreD(laBoutiqueRestaurant, mutableListOf(Reservation(3, today)))

        assertEquals(REJECTED, maitreD.reserves(Reservation(2, today)))
    }

    @Test
    fun existingReservationExceedingOfTableSizeNotAtTheCandidateReservationDate() {
        val maitreD = MaitreD(laBoutiqueRestaurant, mutableListOf(Reservation(5, yesterday)))

        assertEquals(ACCEPTED, maitreD.reserves(Reservation(2, today)))
    }
}
