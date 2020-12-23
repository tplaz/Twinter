package info.interventure.twinter.ui.swipe

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.yuyakaido.android.cardstackview.CardStackLayoutManager
import com.yuyakaido.android.cardstackview.CardStackListener
import com.yuyakaido.android.cardstackview.Direction
import com.yuyakaido.android.cardstackview.StackFrom
import info.interventure.twinter.R
import info.interventure.twinter.dependency.DependencyContainer
import info.interventure.twinter.model.Topic
import info.interventure.twinter.logic.presenter.swipe.SwipePresenter

class SwipeFragment : Fragment(), SwipePresenter.View {
    companion object {
        val TAG = SwipeFragment::class.java.simpleName

        fun newInstance(): SwipeFragment {
            return SwipeFragment()
        }
    }

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
        }
    }

    private lateinit var topicsRecyclerView: RecyclerView

    private val topicAdapter = TopicAdapter()

    private val presenter = DependencyContainer.provideSwipePresenter()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_swipe, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        topicsRecyclerView = view.findViewById(R.id.stackView)
        with(topicsRecyclerView) {
            layoutManager = CardStackLayoutManager(context, cardStackListener).apply {
                setStackFrom(StackFrom.Top)
                setCanScrollVertical(false)
            }
            adapter = topicAdapter
        }

        presenter.onViewCreated(this)
    }

    override fun submitTopics(topics: List<Topic>) {
        topicAdapter.items = topics
    }
}