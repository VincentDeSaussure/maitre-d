package maitre.d

class MaitreD(private var table: Table) {
    fun reserve(candidateReservation: CanditateReservation): String = if (table.seats >= candidateReservation.seats) ACCEPTED else REJECTED
    fun with(reservations: List<Reservation>) {
        for (reservation in reservations) {
            val seats = reservation.seats
            this.table.assign(seats)
        }
    }
}
