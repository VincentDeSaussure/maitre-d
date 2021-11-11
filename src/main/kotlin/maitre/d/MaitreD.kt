package maitre.d

class Restaurant(private var tables: List<Table>) {
    fun from(reservations: List<Reservation>) {
        for (reservation in reservations) {
            val availableTable = this.tables.filter { table -> table.seats > 0 }
            val table = availableTable.first({ table -> table.seats >= reservation.seats })
            table.assign(reservation.seats)
        }
    }

    fun availableSeats(): Int {
        return this.tables.fold(0) { a, b -> a + b.seats }
    }
}

class MaitreD(private var restaurant: Restaurant, private val reservations: MutableList<Reservation>) {

    fun reserves(candidateReservation: CanditateReservation): String {
        restaurant.from(this.reservationsAt(candidateReservation.date))
        return if (restaurant.availableSeats() >= candidateReservation.seats) {
            ACCEPTED
        } else {
            REJECTED
        }
    }

    private fun reservationsAt(date: String): List<Reservation> {
        return this.reservations.filter({ it.date == date })
    }
}
