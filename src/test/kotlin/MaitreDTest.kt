import maitre.d.*
import kotlin.test.Test
import kotlin.test.assertEquals

internal class MaitreDTest {
    @Test
    fun noExistingReservation() {
        val tableSize = 4
        val maitreD = MaitreD(tableSize)

        assertEquals(ACCEPTED, maitreD.reserve(CanditateReservation(4)))
    }

    @Test
    fun noExistingReservationAndTableSizeExceed() {
        val tableSize = 4
        val maitreD = MaitreD(tableSize)

        assertEquals(REJECTED, maitreD.reserve(CanditateReservation(5)))
    }

    @Test
    fun existingReservationAtTheCandidateReservationDateCausesAnExceedOfTableSize() {
        val tableSize = 4
        val maitreD = MaitreD(tableSize)
        val reservations = listOf(Reservation(3))
        maitreD.with(reservations)

        assertEquals(REJECTED, maitreD.reserve(CanditateReservation(2)))
    }
}
