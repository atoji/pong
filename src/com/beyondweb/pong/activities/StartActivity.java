package com.beyondweb.pong.activities;
import com.beyondweb.pong.R;
import com.beyondweb.pong.listeners.StartGameListener;

import android.app.Activity;
import android.os.Bundle;

public class StartActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		setContentView(R.layout.main);
		findViewById(R.id.player).setOnClickListener(new StartGameListener(this, 0, false));
		findViewById(R.id.easy).setOnClickListener(new StartGameListener(this, 10, true));
		findViewById(R.id.medium).setOnClickListener(new StartGameListener(this, 8, true));
		findViewById(R.id.hard).setOnClickListener(new StartGameListener(this, 5, true));
		
		super.onCreate(savedInstanceState);
	}
}
