package com.beyondweb.pong.activities;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.DialogInterface.OnClickListener;
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
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		
		if (resultCode == RESULT_OK &&  requestCode == 15) {
			int winner = data.getIntExtra("winner", 1);
			final boolean isNPC = data.getBooleanExtra("isNPC", true);
			final int dificulty = data.getIntExtra("dificulty", 16);
			
			Builder builder = new AlertDialog.Builder(StartActivity.this);
			builder.setTitle(String.format(getString(R.string.player_wins), winner))
				.setMessage(R.string.play_again)
				.setPositiveButton(R.string.yes, new OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {
						Intent intent = new Intent(StartActivity.this, PongActivity.class);
						intent.putExtra("isNPC", isNPC);
						intent.putExtra("dificulty", dificulty);
						StartActivity.this.startActivityForResult(intent, 15);
					}
				})
				.setNegativeButton(R.string.no, null)
				.show();
			
		}
		
		super.onActivityResult(requestCode, resultCode, data);
	}
}
