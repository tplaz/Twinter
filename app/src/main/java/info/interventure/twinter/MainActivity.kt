package info.interventure.twinter

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import info.interventure.twinter.ui.video.VideoActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val button: Button = findViewById(R.id.video_button)
        button.setOnClickListener {
            startActivity(Intent(this, VideoActivity::class.java))
        }
    }
}
