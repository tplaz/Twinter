package info.interventure.twinter.logic.presenter.swipe

import info.interventure.twinter.logic.data.FirebaseApi
import info.interventure.twinter.model.Topic

class SwipePresenter(
    private val firebaseApi: FirebaseApi
) {

    private var view: View? = null

    fun onCreate(view: View) {
        this.view = view
        firebaseApi.getTopics {
            view.submitTopics(it)
        }
    }

    fun onTopicRejected(topic: Topic) {
        // do nothing
    }

    fun onTopicSelected(topic: Topic) {
        firebaseApi.setTopicSelected(topic)
    }

    interface View {
        fun submitTopics(topics: List<Topic>)
    }
}
