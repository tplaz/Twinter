/*
 * Copyright © 2014-2020, TWINT AG.
 * All rights reserved.
*/
package info.interventure.twinter.model

import com.google.firebase.database.IgnoreExtraProperties

@IgnoreExtraProperties
data class User(
    var name: String? = "",
    var position: String? = "",
    var email: String? = "",
    var room: String? = "",
)
