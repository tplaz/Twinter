package info.interventure.twinter.ui.swipe

import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.yuyakaido.android.cardstackview.CardStackLayoutManager
import com.yuyakaido.android.cardstackview.CardStackListener
import com.yuyakaido.android.cardstackview.Direction
import com.yuyakaido.android.cardstackview.StackFrom
import info.interventure.twinter.R
import info.interventure.twinter.dependency.DependencyContainer
import info.interventure.twinter.model.Topic
import info.interventure.twinter.logic.presenter.swipe.SwipePresenter
import info.interventure.twinter.ui.video.VideoActivity

class SwipeActivity : AppCompatActivity(), SwipePresenter.View {

    private val cardStackListener: CardStackListener = object : CardStackListener {
        var swipedPosition: Int = 0

        override fun onCardDragging(direction: Direction?, ratio: Float) {}

        override fun onCardSwiped(direction: Direction?) {
            direction?.let {
                when(it) {
                    Direction.Left -> presenter.onTopicRejected(topicAdapter.items[swipedPosition])
                    Direction.Right -> presenter.onTopicSelected(topicAdapter.items[swipedPosition])
                    else -> {
                        // only horizontal swipes supported
                    }
                }
            }
        }

        override fun onCardRewound() {}

        override fun onCardCanceled() {}

        override fun onCardAppeared(view: View?, position: Int) {
        }

        override fun onCardDisappeared(view: View?, position: Int) {
            swipedPosition = position  // :(

            if (position == topicAdapter.itemCount - 1) {
                videoButton.visibility = View.VISIBLE
            }
        }
    }

    private lateinit var topicsRecyclerView: RecyclerView

    private lateinit var videoButton: Button

    private val topicAdapter = TopicAdapter()

    private val presenter = DependencyContainer.provideSwipePresenter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_swipe)

        topicsRecyclerView = findViewById(R.id.stackView)
        with(topicsRecyclerView) {
            layoutManager = CardStackLayoutManager(context, cardStackListener).apply {
                setStackFrom(StackFrom.Top)
                setCanScrollVertical(false)
            }
            adapter = topicAdapter
        }
        videoButton = findViewById(R.id.videoButton)

        presenter.onCreate(this)
    }

    override fun submitTopics(topics: List<Topic>) {
        topicAdapter.items = topics
    }

    fun goToVideoActivity(view: View) {
        startActivity(Intent(this, VideoActivity::class.java))
    }
}