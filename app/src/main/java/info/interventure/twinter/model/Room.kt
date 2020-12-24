package info.interventure.twinter.model

class Room(
    val sessionNumber: String = "",
    val users: Map<String, Boolean> = emptyMap()
)
