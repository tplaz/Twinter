package info.interventure.twinter.ui.splash

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import butterknife.BindView
import butterknife.ButterKnife
import info.interventure.twinter.R
import info.interventure.twinter.ui.login.LoginActivity

class SplashActivity : AppCompatActivity() {

    @BindView(R.id.logoImageView)
    lateinit var imageView: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        ButterKnife.bind(this)
    }

    override fun onResume() {
        super.onResume()
        imageView.animate().setDuration(2000).alpha(1f).withEndAction {
            startActivity(Intent(this, LoginActivity::class.java))
        }.start()
    }
}
