package maitre.d

import kotlin.test.Test
import kotlin.test.assertEquals

internal class LaBoutiqueRestaurant {
    val laBoutiqueRestaurant = Restaurant(listOf(CommunalTable(4)))
    val noReservations = mutableListOf<Reservation>()
    @Test
    fun noExistingReservation() {
        val maitreD = MaitreD(laBoutiqueRestaurant, noReservations)

        assertEquals(ACCEPTED, maitreD.reserves(CanditateReservation(4, today)))
    }

    @Test
    fun noExistingReservationAndTableSizeExceed() {
        val maitreD = MaitreD(laBoutiqueRestaurant, noReservations)

        assertEquals(REJECTED, maitreD.reserves(CanditateReservation(5, today)))
    }

    @Test
    fun existingReservationAtTheCandidateReservationDateCausesAnExceedOfTableSize() {
        val maitreD = MaitreD(laBoutiqueRestaurant, mutableListOf(Reservation(3, today)))

        assertEquals(REJECTED, maitreD.reserves(CanditateReservation(2, today)))
    }

    @Test
    fun existingReservationExceedingOfTableSizeNotAtTheCandidateReservationDate() {
        val maitreD = MaitreD(laBoutiqueRestaurant, mutableListOf(Reservation(5, yesterday)))

        assertEquals(ACCEPTED, maitreD.reserves(CanditateReservation(2, today)))
    }
}
