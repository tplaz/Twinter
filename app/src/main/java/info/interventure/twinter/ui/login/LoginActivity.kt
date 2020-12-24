package info.interventure.twinter.ui.login

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import butterknife.BindView
import butterknife.ButterKnife
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.database.ktx.getValue
import com.google.firebase.ktx.Firebase
import info.interventure.twinter.R
import info.interventure.twinter.helpers.DbConstants
import info.interventure.twinter.helpers.LoggedUser
import info.interventure.twinter.model.User
import info.interventure.twinter.ui.video.VideoActivity
import kotlinx.android.synthetic.main.activity_fullscreen.*

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
class LoginActivity : AppCompatActivity() {

    @BindView(R.id.email_editText)
    lateinit var emailEditText: EditText

    @BindView(R.id.go_button)
    lateinit var goButton: Button

    @SuppressLint("ClickableViewAccessibility")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_fullscreen)
        ButterKnife.bind(this)
        initButton()
    }

    private fun initButton() {
        goButton.setOnClickListener {
            val text = email_editText.text.toString()
            if (android.util.Patterns.EMAIL_ADDRESS.matcher(text).matches()) {
                val reference = Firebase.database.getReference(DbConstants.TABLE_USERS)
                reference.orderByChild("email").equalTo(text).addListenerForSingleValueEvent(object :
                    ValueEventListener {
                    override fun onDataChange(snapshot: DataSnapshot) {
                        val user = snapshot.getValue<User>()
                        if (user != null) {
                            LoggedUser.isLogged = true
                            LoggedUser.email = user.email
                            startWizard()
                        } else {
                            Snackbar.make(
                                emailEditText, "Looks like this e-mail is not registered!", Snackbar
                                    .LENGTH_INDEFINITE
                            )
                            startWizard()
                        }
                    }

                    override fun onCancelled(error: DatabaseError) {
                    }
                })
            } else {
                Toast.makeText(this, "Not a valid email, please use a valid one!", Toast.LENGTH_LONG).show()
            }
        }
    }

    private fun isValidUser(text: Editable?): Boolean? {
        return text?.endsWith("interventure.info")
    }

    private fun startWizard() {
        // magic
        startActivity(Intent(this, VideoActivity::class.java))
    }
}
