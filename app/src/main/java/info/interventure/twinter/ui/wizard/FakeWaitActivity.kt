package info.interventure.twinter.ui.wizard

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import info.interventure.twinter.R
import info.interventure.twinter.ui.swipe.SwipeActivity

class FakeWaitActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fake_wait)
    }

    override fun onResume() {
        super.onResume()
        Handler().postDelayed({
            startActivity(Intent(this, SwipeActivity::class.java))
            overridePendingTransition(R.anim.slide_out_left, R.anim.slide_in_right)
        }, 3000)
    }
}
