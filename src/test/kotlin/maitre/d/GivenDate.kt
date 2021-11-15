package maitre.d

import java.time.LocalDateTime
import java.time.LocalTime

val firstSeating = LocalTime.of(20, 0)

val today = LocalDateTime.now().with(firstSeating)
val yesterday = today.minusDays(1).with(firstSeating)
