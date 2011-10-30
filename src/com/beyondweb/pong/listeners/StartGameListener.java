package com.beyondweb.pong.listeners;

import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;

import com.beyondweb.pong.activities.PongActivity;

public class StartGameListener implements OnClickListener {

	private final int dificulty;
	private final boolean isNPC;
	private final Activity activity;

	public StartGameListener(Activity activity,  int dificulty, boolean isNPC) {
		this.activity = activity;
		this.dificulty = dificulty;
		this.isNPC = isNPC;
	}

	@Override
	public void onClick(View v) {
		Intent intent = new Intent(activity, PongActivity.class);
		intent.putExtra("isNPC", isNPC);
		intent.putExtra("dificulty", dificulty);
		activity.startActivityForResult(intent, 15);
	}

}
