package info.interventure.twinter.ui.video

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.floatingactionbutton.FloatingActionButton
import info.interventure.twinter.R
import info.interventure.twinter.helpers.Constants
import info.interventure.twinter.helpers.JoinMeetingHelper
import us.zoom.sdk.MeetingService
import us.zoom.sdk.MeetingServiceListener
import us.zoom.sdk.MeetingStatus
import us.zoom.sdk.StartMeetingOptions
import us.zoom.sdk.StartMeetingParamsWithoutLogin
import us.zoom.sdk.ZoomError
import us.zoom.sdk.ZoomSDK
import us.zoom.sdk.ZoomSDKInitParams
import us.zoom.sdk.ZoomSDKInitializeListener

class VideoActivity : AppCompatActivity(), ZoomSDKInitializeListener, MeetingServiceListener {

    companion object {
        private val TAG = "Zoom SDK Example"

        val ACTION_RETURN_FROM_MEETING = "us.zoom.sdkexample2.action.ReturnFromMeeting"
        val EXTRA_TAB_ID = "tabId"

        val TAB_WELCOME = 1
        val TAB_MEETING = 2
        val TAB_PAGE_2 = 3
        val MEETING_ID = "8674204370"
        val USER_ID = ""
        val ZOOM_ACCESS_TOKEN = ""

        private val STYPE = MeetingService.USER_TYPE_API_USER
        private val DISPLAY_NAME = "ZoomUS SDK"
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
        initParams.appKey = Constants.SDK_KEY
        initParams.appSecret = Constants.SDK_SECRET
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

    fun startMeeting() {
        val zoomSDK = ZoomSDK.getInstance()
        if (!zoomSDK.isInitialized) {
            Toast.makeText(this, "ZoomSDK has not been initialized successfully", Toast.LENGTH_LONG).show()
            return
        }
        if (MEETING_ID == null) {
            Toast.makeText(this, "MEETING_ID in Constants can not be NULL", Toast.LENGTH_LONG).show()
            return
        }
        val meetingService = zoomSDK.meetingService
        val opts = StartMeetingOptions()
        opts.no_driving_mode = true
        //		opts.no_meeting_end_message = true;
        opts.no_titlebar = true
        opts.no_bottom_toolbar = true
        opts.no_invite = true
        val params = StartMeetingParamsWithoutLogin()
        params.userId = USER_ID
        params.zoomAccessToken = ZOOM_ACCESS_TOKEN
        params.meetingNo = MEETING_ID
        params.displayName = DISPLAY_NAME
        val ret = meetingService.startMeetingWithParams(this, params, opts)
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

        JoinMeetingHelper.instance?.joinMeetingWithNumber(this, meetingNo, meetingPassword)
    }

    override fun onMeetingStatusChanged(p0: MeetingStatus?, p1: Int, p2: Int) {
    }
}
