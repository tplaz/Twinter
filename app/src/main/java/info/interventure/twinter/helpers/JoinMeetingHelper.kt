package info.interventure.twinter.helpers

import android.content.Context
import us.zoom.sdk.JoinMeetingOptions
import us.zoom.sdk.JoinMeetingParams
import us.zoom.sdk.ZoomSDK

class JoinMeetingHelper private constructor() {

    private val mZoomSDK: ZoomSDK = ZoomSDK.getInstance()

    fun joinMeetingWithNumber(context: Context?, meetingNo: String?, meetingPassword: String?): Int {
        val ret = -1
        val meetingService = mZoomSDK.meetingService ?: return ret
        val opts: JoinMeetingOptions = ZoomMeetingUISettingHelper.joinMeetingOptions
        val params = JoinMeetingParams()
        params.displayName = DISPLAY_NAME
        params.meetingNo = meetingNo
        return ApiUserStartMeetingHelper.getInstance().startMeetingWithNumber(context, meetingNo)
    }

    fun joinMeetingWithVanityId(context: Context?, vanityId: String?, meetingPassword: String?): Int {
        val ret = -1
        val meetingService = mZoomSDK.meetingService ?: return ret
        val opts: JoinMeetingOptions = ZoomMeetingUISettingHelper.joinMeetingOptions
        val params = JoinMeetingParams()
        params.displayName = DISPLAY_NAME
        params.vanityID = vanityId
        params.password = meetingPassword
        return meetingService.joinMeetingWithParams(context, params, opts)
    }

    companion object {
        private const val TAG = "JoinMeetingHelper"
        private var mJoinMeetingHelper: JoinMeetingHelper? = null
        private const val DISPLAY_NAME = "ZoomUS_SDK"

        @get:Synchronized
        val instance: JoinMeetingHelper?
            get() {
                mJoinMeetingHelper = JoinMeetingHelper()
                return mJoinMeetingHelper
            }
    }
}
