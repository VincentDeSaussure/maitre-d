package maitre.d

import java.time.LocalDateTime

class Restaurant(private var tables: List<Table>, val seatingDuration: Long) {
    private lateinit var reservations: List<Reservation>

    private fun findSmallestTableFor(seats: Int) =
        tables.filter { it.seats >= seats }.minByOrNull { it.seats }

    fun with(reservations: List<Reservation>): Restaurant {
        this.reservations = reservations
        return this
    }

    fun at(atDate: LocalDateTime) {
        for (reservation in reservationDuring(atDate)) {
            findSmallestTableFor(reservation.seats)?.assign(reservation.seats)
        }
    }

    private fun reservationDuring(dateTime: LocalDateTime): List<Reservation> {
        val aSeatingBefore = dateTime.minusHours(seatingDuration)
        val aSeatingAfter = dateTime.plusHours(seatingDuration)
        return reservations.filter { aSeatingBefore.isBefore(it.date) && it.date.isBefore(aSeatingAfter) }
    }

    private fun haveAvailableTableFor(candidateReservation: Reservation) =
        findSmallestTableFor(candidateReservation.seats) != null

    fun canAccept(candidate: Reservation): String {
        return if (haveAvailableTableFor(candidate)) {
            ACCEPTED
        } else {
            REJECTED
        }
    }
}

class MaitreD(private var restaurant: Restaurant, private val reservations: MutableList<Reservation>) {

    fun reserves(candidate: Reservation): String {
        restaurant.with(reservations).at(candidate.date)
        return restaurant.canAccept(candidate)
    }
}
