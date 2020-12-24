package info.interventure.twinter.ui.wizard

import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import butterknife.BindView
import butterknife.ButterKnife
import info.interventure.twinter.R

class Step1WizActivity : AppCompatActivity() {

    @BindView(R.id.step_1_next)
    lateinit var next: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_step1_wiz)
        ButterKnife.bind(this)
        next.setOnClickListener {
            startActivity(Intent(this, Step2WizActivity::class.java))
            overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left)
        }
    }
}
