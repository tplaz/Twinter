package info.interventure.twinter

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import info.interventure.twinter.ui.swipe.SwipeFragment
import android.widget.Button
import info.interventure.twinter.ui.video.VideoActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (supportFragmentManager.findFragmentByTag(SwipeFragment.TAG) == null) {
            supportFragmentManager
                .beginTransaction()
                .add(android.R.id.content, SwipeFragment.newInstance(), SwipeFragment.TAG)
                .commit()
        }
        val button: Button = findViewById(R.id.video_button)
        button.setOnClickListener {
            startActivity(Intent(this, VideoActivity::class.java))
        }
    }
}
