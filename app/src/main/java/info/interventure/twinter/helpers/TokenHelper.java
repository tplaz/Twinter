package info.interventure.twinter.helpers;

import android.util.Base64;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import javax.net.ssl.HttpsURLConnection;

class TokenHelper {

	public String getToken() {
		long time = System.currentTimeMillis() / 1000;

		String header = "{\"alg\": \"HS256\", \"typ\": \"JWT\"}";
		String payload =
				"{\"iss\": \"" + APIUserConstants.API_KEY + "\"" + ", \"exp\": " + String.valueOf(
						time) + "}";
		try {
			String headerBase64Str = Base64.encodeToString(header.getBytes("utf-8"),
					Base64.NO_WRAP | Base64.NO_PADDING | Base64.URL_SAFE);
			String payloadBase64Str = Base64.encodeToString(payload.getBytes("utf-8"),
					Base64.NO_WRAP | Base64.NO_PADDING | Base64.URL_SAFE);
			final Mac mac = Mac.getInstance("HmacSHA256");
			SecretKeySpec secretKeySpec = new SecretKeySpec(APIUserConstants.API_SECRET.getBytes(),
					"HmacSHA256");
			mac.init(secretKeySpec);

			byte[] digest = mac.doFinal((headerBase64Str + "." + payloadBase64Str).getBytes());

			return headerBase64Str + "." + payloadBase64Str + "." + Base64.encodeToString(digest,
					Base64.NO_WRAP | Base64.NO_PADDING | Base64.URL_SAFE);
		}
		catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		catch (InvalidKeyException e) {
			e.printStackTrace();
		}
		return null;
	}

	public String getZAK(String userId, String jwtAccessToken) throws IOException, JSONException {
		URL zoomTokenEndpoint = new URL(
				"https://api.zoom.us/v2/users/" + userId + "/token?type=zak&access_token=" +
						jwtAccessToken);
		HttpsURLConnection connection = (HttpsURLConnection) zoomTokenEndpoint.openConnection();

		if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
			InputStream responseBody = connection.getInputStream();
			InputStreamReader responseBodyReader = new InputStreamReader(responseBody, "UTF-8");
			BufferedReader streamReader = new BufferedReader(responseBodyReader);
			StringBuilder responseStrBuilder = new StringBuilder();

			//get JSON String
			String inputStr;
			while ((inputStr = streamReader.readLine()) != null) {
				responseStrBuilder.append(inputStr);
			}

			connection.disconnect();
			JSONObject jsonObject = new JSONObject(responseStrBuilder.toString());
			return jsonObject.getString("token");
		}
		else {
			Log.d("Deki", "error in connection");
			return null;
		}
	}
}
