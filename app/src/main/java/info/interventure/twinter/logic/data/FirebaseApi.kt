package info.interventure.twinter.logic.data

import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.database.ktx.getValue
import com.google.firebase.ktx.Firebase
import info.interventure.twinter.helpers.DbConstants
import info.interventure.twinter.helpers.LoggedUser
import info.interventure.twinter.model.Topic
import info.interventure.twinter.model.TopicFb
import info.interventure.twinter.model.User

class FirebaseApi {

    private val topicTable
        get() = Firebase.database.getReference(DbConstants.TABLE_TOPICS)
    private val userTable
        get() = Firebase.database.getReference(DbConstants.TABLE_USERS)

    fun getTopics(topicsListener: (List<Topic>) -> Unit) {
        topicTable.addListenerForSingleValueEvent(object :
            ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val topicsMap = snapshot.getValue<Map<String, TopicFb>>()
                topicsListener.invoke(topicsMap?.flatMap {
                    listOf(Topic.from(it.value, it.key))
                } ?: emptyList())
            }

            override fun onCancelled(error: DatabaseError) {
                // do nothing
            }
        })
    }

    fun setTopicSelected(topic: Topic) {
        val userId = LoggedUser.userId ?: return

        userTable.child(userId).child("topics").updateChildren(mapOf(topic.id to true))
        topicTable.child(topic.id).child("users").updateChildren(mapOf(userId to true))
    }
}