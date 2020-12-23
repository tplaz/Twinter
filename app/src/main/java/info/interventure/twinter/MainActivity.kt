package info.interventure.twinter

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import info.interventure.twinter.ui.swipe.SwipeFragment

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
    }
}