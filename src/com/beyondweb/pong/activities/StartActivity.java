package com.beyondweb.pong.activities;
import android.app.Activity;
import android.graphics.Typeface;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.beyondweb.pong.R;
import com.beyondweb.pong.listeners.StartGameListener;

public class StartActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		setContentView(R.layout.main);
		
		((TextView) findViewById(R.id.title)).setTypeface(Typeface.createFromAsset(getAssets(), "font/atari.ttf"));
		
		findViewById(R.id.player).setOnClickListener(new StartGameListener(this, 0, false));
		((Button) findViewById(R.id.player)).setTypeface(Typeface.createFromAsset(getAssets(), "font/atari.ttf"));
		
		findViewById(R.id.easy).setOnClickListener(new StartGameListener(this, 16, true));
		((Button) findViewById(R.id.easy)).setTypeface(Typeface.createFromAsset(getAssets(), "font/atari.ttf"));
		
		findViewById(R.id.medium).setOnClickListener(new StartGameListener(this, 12, true));
		((Button) findViewById(R.id.medium)).setTypeface(Typeface.createFromAsset(getAssets(), "font/atari.ttf"));
		
		findViewById(R.id.hard).setOnClickListener(new StartGameListener(this, 8, true));
		((Button) findViewById(R.id.hard)).setTypeface(Typeface.createFromAsset(getAssets(), "font/atari.ttf"));
		
		super.onCreate(savedInstanceState);
	}
}
