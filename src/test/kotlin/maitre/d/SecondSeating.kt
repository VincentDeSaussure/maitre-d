package maitre.d

import org.junit.Test
import java.time.LocalTime
import kotlin.test.assertEquals

internal class SecondSeating {
    @Test
    fun secondSeatingAcceptableDuration() {
        val seatingDuration = 2
        val secondBreathe = Restaurant(listOf(ExclusiveTable(3)), seatingDuration)
        val firstSeating = LocalTime.of(18, 0)
        val maitreD = MaitreD(secondBreathe, mutableListOf(Reservation(2, today, firstSeating)))

        assertEquals(
            maitreD.reserves(CanditateReservation(1, today)),
            ACCEPTED
        )
    }
}
