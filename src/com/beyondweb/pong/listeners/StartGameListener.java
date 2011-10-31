package com.beyondweb.pong.listeners;

import static com.beyondweb.pong.util.Constants.PONG_REQUEST_CODE;
import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;

import com.beyondweb.pong.activities.PongActivity;

public class StartGameListener implements OnClickListener {

	private final int difficulty;
	private final boolean isNPC;
	private final Activity activity;

	public StartGameListener(Activity activity,  int difficulty, boolean isNPC) {
		this.activity = activity;
		this.difficulty = difficulty;
		this.isNPC = isNPC;
	}

	@Override
	public void onClick(View v) {
		Intent intent = new Intent(activity, PongActivity.class);
		intent.putExtra("isNPC", isNPC);
		intent.putExtra("difficulty", difficulty);
		activity.startActivityForResult(intent, PONG_REQUEST_CODE);
	}

}
