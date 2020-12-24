package info.interventure.twinter.ui.video;

import android.os.AsyncTask;
import android.util.Log;

import info.interventure.twinter.helpers.APIUserInfo;
import info.interventure.twinter.helpers.APIUserInfoHelper;

class RetrieveUserInfoTask extends AsyncTask<String, Void, APIUserInfo> {

	@Override
	protected void onPreExecute() {
		super.onPreExecute();
	}

	@Override
	protected APIUserInfo doInBackground(String... params) {
		String userId = APIUserInfoHelper.getUserId(params[0]);
		Log.d("Deki", "userId: " + userId);
		String accessToken = APIUserInfoHelper.getZoomAccessToken(userId);

		if (accessToken != null && !accessToken.isEmpty()) {
			APIUserInfo apiUserInfo = new APIUserInfo(params[0], accessToken);
			APIUserInfoHelper.saveAPIUserInfo(apiUserInfo);
			return apiUserInfo;
		}
		else {
			return null;
		}
	}

	@Override
	protected void onPostExecute(APIUserInfo apiUserInfo) {
		super.onPostExecute(apiUserInfo);
		if (apiUserInfo == null) {
			Log.d("Deki", "Faild to retrieve Api user info!");
		}
	}
}
