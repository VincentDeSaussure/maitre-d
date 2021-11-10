import maitre.d.*
import kotlin.test.Test
import kotlin.test.assertEquals

internal class MaitreDTest {
    val table = Table(4)
    @Test
    fun noExistingReservation() {
        val maitreD = MaitreD(table)

        assertEquals(ACCEPTED, maitreD.reserve(CanditateReservation(4)))
    }

    @Test
    fun noExistingReservationAndTableSizeExceed() {
        val maitreD = MaitreD(table)

        assertEquals(REJECTED, maitreD.reserve(CanditateReservation(5)))
    }

    @Test
    fun existingReservationAtTheCandidateReservationDateCausesAnExceedOfTableSize() {
        val maitreD = MaitreD(table)
        maitreD.with(listOf(Reservation(3)))

        assertEquals(REJECTED, maitreD.reserve(CanditateReservation(2)))
    }
}
