package info.interventure.twinter.model

data class TopicFb(
    val name: String,
    val description: String,
    val users: Map<String, Boolean>
) {
    constructor() : this("", "", emptyMap())
}