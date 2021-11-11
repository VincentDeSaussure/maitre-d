package maitre.d

import java.time.LocalDateTime

class Restaurant(private var tables: List<Table>, seatingDuration: Int = 2) {
    fun from(reservations: List<Reservation>) {
        for (reservation in reservations) {
            val availableTable = this.tables.filter { table -> table.seats > 0 }
            val table = availableTable.filter { it.seats >= reservation.seats }.minByOrNull { it.seats  }
            table?.assign(reservation.seats)
        }
    }

    private fun availableSeats(): Int {
        return this.tables.fold(0) { a, b -> a + b.seats }
    }

    fun assign(candidateReservation: CanditateReservation): Boolean {
        return this.tables.any { it.seats >= candidateReservation.seats }
    }
}

class MaitreD(private var restaurant: Restaurant, private val reservations: MutableList<Reservation>) {

    fun reserves(candidateReservation: CanditateReservation): String {
        restaurant.from(this.reservationsAt(candidateReservation.date))
        return if (restaurant.assign(candidateReservation)) {
            ACCEPTED
        } else {
            REJECTED
        }
    }

    private fun reservationsAt(date: LocalDateTime): List<Reservation> {
        return this.reservations.filter({ date.isEqual( it.date )})
    }
}
