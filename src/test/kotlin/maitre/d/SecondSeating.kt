package maitre.d

import org.junit.Test
import java.time.LocalTime
import kotlin.test.assertEquals

internal class SecondSeating {
    @Test
    fun secondSeatingCandidateReservationBeforeFirstSeatingEndReservation() {
        val seatingDuration = 2
        val newBreath = Restaurant(listOf(ExclusiveTable(2)), seatingDuration.toLong())
        val maitreD = MaitreD(newBreath, mutableListOf(Reservation(2, today.with(LocalTime.of(18, 15)))))

        val candidateReservationBefore = Reservation(2, today.with(LocalTime.of(20, 0)))
        assertEquals(
            REJECTED,
            maitreD.reserves(candidateReservationBefore)
        )
    }

    @Test
    fun secondSeatingAcceptableDurationAfterReservation() {
        val seatingDuration = 2
        val newBreath = Restaurant(listOf(ExclusiveTable(2)), seatingDuration.toLong())
        val maitreD = MaitreD(newBreath, mutableListOf(Reservation(2, today.with(LocalTime.of(18, 0)))))
        val candidateReservationAfter = Reservation(2, today.with(LocalTime.of(20, 0)))
        assertEquals(
            ACCEPTED,
            maitreD.reserves(candidateReservationAfter)
        )
    }
}
