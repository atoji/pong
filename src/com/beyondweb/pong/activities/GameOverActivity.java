package com.beyondweb.pong.activities;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.beyondweb.pong.R;

public class GameOverActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		setContentView(R.layout.main);
		
		((TextView) findViewById(R.id.title)).setTypeface(Typeface.createFromAsset(getAssets(), "font/atari.ttf"));
		((Button) findViewById(R.id.player)).setTypeface(Typeface.createFromAsset(getAssets(), "font/atari.ttf"));
		((Button) findViewById(R.id.easy)).setTypeface(Typeface.createFromAsset(getAssets(), "font/atari.ttf"));
		((Button) findViewById(R.id.medium)).setTypeface(Typeface.createFromAsset(getAssets(), "font/atari.ttf"));
		((Button) findViewById(R.id.hard)).setTypeface(Typeface.createFromAsset(getAssets(), "font/atari.ttf"));
		
		final boolean isNPC = getIntent().getExtras().getBoolean("isNPC");
		final int dificulty = getIntent().getExtras().getInt("dificulty");
		final int winner = getIntent().getExtras().getInt("winner");
		
		Builder builder = new AlertDialog.Builder(GameOverActivity.this);
		builder.setTitle(String.format(getString(R.string.player_wins), winner))
				.setMessage(R.string.play_again)
				.setPositiveButton(R.string.yes, new OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {
						Intent intent = new Intent(GameOverActivity.this, PongActivity.class);
						intent.putExtra("isNPC", isNPC);
						intent.putExtra("dificulty", dificulty);
						GameOverActivity.this.startActivity(intent);
						GameOverActivity.this.finish();
					}
				})
				.setNegativeButton(R.string.no, new OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {
						GameOverActivity.this.finish();
					}
				})
				.show();
		
		super.onCreate(savedInstanceState);
	}
}
