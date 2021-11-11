package maitre.d

import java.time.LocalDateTime
import java.time.LocalTime

class Reservation(val seats: Int, val date: LocalDateTime, val time: LocalTime = LocalTime.of(20, 0)) {}
