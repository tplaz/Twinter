package info.interventure.twinter.logic.data

import info.interventure.twinter.model.Topic

class FirebaseApi {

    fun getTopics(): List<Topic> {
        return listOf(
            Topic("Fudbal"),
            Topic("Odbojka"),
            Topic("Tenis"),
            Topic("MMA"),
            Topic("Kosarka"),
            Topic("Snuker"),
            Topic("Skijaski skokovi"),
        )
    }

    fun setTopicSelected(topic: Topic) {
        selectedTopics.add(topic)
    }

    private val selectedTopics: MutableSet<Topic> = mutableSetOf()
}