package maitre.d

class MaitreD(private var tableSize: Int) {
    fun reserve(candidateReservation: CanditateReservation): String = if (tableSize >= candidateReservation.seats) ACCEPTED else REJECTED
    fun with(reservations: List<Reservation>) {
        for (reservation in reservations) {
            val seats = reservation.seats
            this.tableSize -= seats
        }
    }
}
