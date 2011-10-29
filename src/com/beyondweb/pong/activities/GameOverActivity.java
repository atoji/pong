package com.beyondweb.pong.activities;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.os.Bundle;

import com.beyondweb.pong.R;

public class GameOverActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		setContentView(R.layout.main);
		
		final boolean isNPC = getIntent().getExtras().getBoolean("isNPC");
		final int dificulty = getIntent().getExtras().getInt("dificulty");
		final int winner = getIntent().getExtras().getInt("winner");
		
		Builder builder = new AlertDialog.Builder(GameOverActivity.this);
		builder.setTitle(String.format("Player %d wins!", winner))
				.setMessage("Play again?")
				.setPositiveButton("Yes!", new OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {
						Intent intent = new Intent(GameOverActivity.this, PongActivity.class);
						intent.putExtra("isNPC", isNPC);
						intent.putExtra("dificulty", dificulty);
						GameOverActivity.this.startActivity(intent);
						GameOverActivity.this.finish();
					}
				})
				.setNegativeButton("No", new OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {
						GameOverActivity.this.finish();
					}
				})
				.show();
		
		super.onCreate(savedInstanceState);
	}
}
