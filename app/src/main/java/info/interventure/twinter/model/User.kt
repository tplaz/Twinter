/*
 * Copyright © 2014-2020, TWINT AG.
 * All rights reserved.
*/
package info.interventure.twinter.model

import com.google.firebase.database.IgnoreExtraProperties

@IgnoreExtraProperties
class User {
    var name: String? = null
    var position: String? = null
    var email: String? = null
    var room: String? = null
}
