package info.interventure.twinter.model

import com.google.firebase.database.IgnoreExtraProperties

@IgnoreExtraProperties
class User {
    var name: String? = null
    var position: String? = null
    var email: String? = null
    var room: String? = null
}
