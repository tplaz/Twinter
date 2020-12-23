package info.interventure.twinter.helpers

interface Constants {
    companion object {
        /**
         * We recommend that, you can generate jwttoken on your own server instead of hardcore in the code.
         * We hardcore it here, just to run the demo.
         *
         * You can generate a jwttoken on the https://jwt.io/
         * with this payload:
         * {
         * "appKey": "string", // app key
         * "iat": long, // access token issue timestamp
         * "exp": long, // access token expire time
         * "tokenExp": long // token expire time
         * }
         */
        const val SDK_KEY = "AswGc1rbfj5dqBLECsRciI0PbAH2nUXQuZux"
        const val SDK_SECRET = "H3VmcsdTyzdrGPi4iD4pPiXtdvF0VlHNhMZz"

        // TODO Change it to your web domain
        const val WEB_DOMAIN = "zoom.us"

        // TODO change it to your user ID
        const val USER_ID = "Your user ID from REST API"

        // TODO change it to your token
        const val ZOOM_ACCESS_TOKEN = "Your zak from REST API"

        // TODO Change it to your exist meeting ID to start meeting
        val MEETING_ID: String? = null

    }
}
