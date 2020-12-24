package info.interventure.twinter.ui.swipe

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.recyclerview.widget.RecyclerView
import butterknife.BindView
import butterknife.ButterKnife
import com.yuyakaido.android.cardstackview.CardStackLayoutManager
import com.yuyakaido.android.cardstackview.CardStackListener
import com.yuyakaido.android.cardstackview.Direction
import com.yuyakaido.android.cardstackview.StackFrom
import info.interventure.twinter.R
import info.interventure.twinter.dependency.DependencyContainer
import info.interventure.twinter.logic.presenter.swipe.SwipePresenter
import info.interventure.twinter.model.Topic
import info.interventure.twinter.videortc.WaitingRoomActivity

class SwipeActivity : AppCompatActivity(), SwipePresenter.View {

    @BindView(R.id.container)
    lateinit var container: ViewGroup

    @BindView(R.id.drawer_layout)
    lateinit var drawer: DrawerLayout

    @BindView(R.id.userInfo_imageView)
    lateinit var icon: ImageView

    private val cardStackListener: CardStackListener = object : CardStackListener {
        var swipedPosition: Int = 0

        override fun onCardDragging(direction: Direction?, ratio: Float) {}

        override fun onCardSwiped(direction: Direction?) {
            direction?.let {
                when (it) {
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
                goToVideoActivity()
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
        ButterKnife.bind(this)
        topicsRecyclerView = findViewById(R.id.stackView)
        with(topicsRecyclerView) {
            layoutManager = CardStackLayoutManager(context, cardStackListener).apply {
                setStackFrom(StackFrom.Top)
                setCanScrollVertical(false)
            }
            adapter = topicAdapter
        }
        videoButton = findViewById(R.id.videoButton)
        videoButton.setOnClickListener {
            goToVideoActivity()
        }

        icon.setOnClickListener {
            drawer.openDrawer(GravityCompat.START)
        }

        presenter.onCreate(this)
    }

    override fun submitTopics(topics: List<Topic>) {
        topicAdapter.items = topics
    }

    fun goToVideoActivity() {
        startActivity(Intent(this, WaitingRoomActivity::class.java))
    }
}
