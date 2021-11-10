package maitre.d

class Table(var seats: Int) {
    fun assign(seats: Int) {
        this.seats -= seats
    }
}
