package info.interventure.twinter.ui.swipe

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import info.interventure.twinter.R
import info.interventure.twinter.model.Topic
import kotlin.properties.Delegates

class TopicAdapter: RecyclerView.Adapter<TopicAdapter.TopicViewHolder>() {

    var items: List<Topic> by Delegates.observable(emptyList()) { _, old, new ->
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TopicViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(
            R.layout.topic_card_layout, parent, false
        )
        return TopicViewHolder(view)
    }

    override fun onBindViewHolder(holder: TopicViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int = items.size

    inner class TopicViewHolder(view: View): RecyclerView.ViewHolder(view) {

        private val topicNameView: TextView = view.findViewById(R.id.topicName)

        fun bind(topic: Topic) {
            topicNameView.text = topic.name
        }
    }
}