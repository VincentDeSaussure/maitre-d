package maitre.d

sealed class Table(var seats: Int) {
    init {
        require(seats > 0, { "A Table should have a seat" })
    }

    open fun assign(seats: Int) {
        throw IllegalArgumentException("A table should have a assign implementation")
    }
}

class ExclusiveTable(seats: Int): Table(seats) {
    override fun assign(seats: Int) {
        this.seats = 0
    }
}

class CommunalTable(seats: Int): Table(seats) {
    override fun assign(seats: Int) {
        this.seats -= seats
    }
}
