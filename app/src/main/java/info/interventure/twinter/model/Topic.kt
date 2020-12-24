package info.interventure.twinter.model

data class Topic(
    val id: String,
    val name: String,
    val description: String
) {
    companion object {
        fun from(topicFb: TopicFb, id: String) = Topic(
            id = id,
            name = topicFb.name,
            description = topicFb.description
        )
    }
}