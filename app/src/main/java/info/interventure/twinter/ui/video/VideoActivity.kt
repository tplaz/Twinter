package info.interventure.twinter.ui.video

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.floatingactionbutton.FloatingActionButton
import info.interventure.twinter.R
import info.interventure.twinter.helpers.APIUserConstants
import info.interventure.twinter.helpers.APIUserInfoHelper
import info.interventure.twinter.helpers.Constants
import info.interventure.twinter.helpers.JoinMeetingHelper
import us.zoom.sdk.MeetingServiceListener
import us.zoom.sdk.MeetingStatus
import us.zoom.sdk.ZoomError
import us.zoom.sdk.ZoomSDK
import us.zoom.sdk.ZoomSDKInitParams
import us.zoom.sdk.ZoomSDKInitializeListener

class VideoActivity : AppCompatActivity(), ZoomSDKInitializeListener, MeetingServiceListener {

    companion object {
        private val TAG = "Zoom SDK Example"

        val MEETING_ID = "8674204370"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_video)
        setSupportActionBar(findViewById(R.id.toolbar))

        findViewById<FloatingActionButton>(R.id.fab).setOnClickListener { view ->
            joinMeeting()
        }
        initZoomSdk()
    }

    private fun initZoomSdk() {
        val zoomSDK = ZoomSDK.getInstance()

        val initParams = ZoomSDKInitParams()
        initParams.appKey = APIUserConstants.API_KEY
        initParams.appSecret = APIUserConstants.API_SECRET
        initParams.domain = Constants.WEB_DOMAIN

        zoomSDK.initialize(this, this, initParams)

        if (zoomSDK.isInitialized) {
            registerMeetingServiceListener()
        }
    }

    override fun onZoomSDKInitializeResult(errorCode: Int, internalErrorCode: Int) {
        Log.i(
            TAG,
            "onZoomSDKInitializeResult, errorCode=$errorCode, internalErrorCode=$internalErrorCode"
        )

        if (errorCode != ZoomError.ZOOM_ERROR_SUCCESS) {
            Toast.makeText(
                this,
                "Failed to initialize Zoom SDK. Error: $errorCode, internalErrorCode=$internalErrorCode",
                Toast.LENGTH_LONG
            )
        } else {
            Toast.makeText(this, "Initialize Zoom SDK successfully.", Toast.LENGTH_LONG).show()
            if (APIUserInfoHelper.getAPIUserInfo() == null) {
                val task = RetrieveUserInfoTask() //retrieve api user token
                task.execute(APIUserConstants.USER_ID)
            }
            registerMeetingServiceListener()
        }
    }

    override fun onZoomAuthIdentityExpired() {
    }

    private fun registerMeetingServiceListener() {
        val zoomSDK = ZoomSDK.getInstance()
        val meetingService = zoomSDK.meetingService
        meetingService?.addListener(this)
    }

    fun joinMeeting() {
        val meetingNo: String = MEETING_ID
        val meetingPassword: String = "3fDXLV"

        val zoomSDK = ZoomSDK.getInstance()

        if (!zoomSDK.isInitialized) {
            Toast.makeText(this, "ZoomSDK has not been initialized successfully", Toast.LENGTH_LONG).show()
            return
        }

        val meetingService = zoomSDK.meetingService

        val status = JoinMeetingHelper.instance?.joinMeetingWithNumber(this, "1234567899", "Peric")
        Log.d(TAG, "status: $status")
    }

    override fun onMeetingStatusChanged(p0: MeetingStatus?, p1: Int, p2: Int) {
    }
}


