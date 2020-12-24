/*
 * Copyright 2018, The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package info.interventure.twinter.videortc;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import info.interventure.twinter.R;
import info.interventure.twinter.dependency.DependencyContainer;
import info.interventure.twinter.logic.data.FirebaseApi;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;

public class WaitingRoomActivity extends Activity {

	private static final String TAG = WaitingRoomActivity.class.getSimpleName();

	private FirebaseApi firebaseApi = DependencyContainer.INSTANCE.provideFirebaseApi();
	private Function1<? super String, Unit> onRoomChangeListener = roomId -> {
		Intent myIntent = new Intent(WaitingRoomActivity.this, CallActivity.class);
		myIntent.putExtra(CallActivity.MY_ROOM_KEY, roomId);
		startActivity(myIntent);
		return null;
	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Log.d(TAG, "onCreate");
		setContentView(R.layout.activity_waiting_room);

		firebaseApi.subscribeToUserChanges(onRoomChangeListener);
	}
}