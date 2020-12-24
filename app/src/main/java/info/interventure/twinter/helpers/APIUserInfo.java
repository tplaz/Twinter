package info.interventure.twinter.helpers;

/*
 * Copyright © 2014-2020, TWINT AG.
 * All rights reserved.
 */
public class APIUserInfo {

	public String userId;
	public String userZoomAccessToken;

	public APIUserInfo(String userId, String userZoomAccessToken) {
		this.userId = userId;
		this.userZoomAccessToken = userZoomAccessToken;
	}
}
