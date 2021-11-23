package maitre.d

interface Table {
    fun capacity(): Int
    fun assign(seats: Int)
    fun available(seats: Int): Boolean
}

class ExclusiveTable(private val seats: Int): Table {
    init {
        require(seats > 0, { "A Table should have at least one seat" })
    }

    private var isReserved: Boolean = false
    override fun capacity(): Int = seats

    override fun assign(seats: Int) {
        if (available(seats)) this.isReserved = true
    }

    override fun available(seats: Int): Boolean = !isReserved && this.affords(seats)

    private fun affords(testSeats: Int): Boolean = seats >= testSeats
}

class CommunalTable(private var seats: Int): Table {
    init {
        require(seats > 0, { "A Table should have at least one seat" })
    }

    override fun capacity(): Int = seats

    override fun assign(seats: Int) {
        if (available(seats)) this.seats -= seats
    }

    override fun available(testSeats: Int) = seats >= testSeats
}
