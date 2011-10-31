package com.beyondweb.pong.model;

import org.anddev.andengine.audio.sound.Sound;
import org.anddev.andengine.entity.scene.Scene;
import org.anddev.andengine.entity.text.ChangeableText;
import org.anddev.andengine.opengl.font.Font;

import android.app.Activity;
import android.content.Intent;
import android.view.Display;

import com.beyondweb.pong.activities.PongActivity;


public class ScoreBoard {

	private static final int MAX_POINTS = 9;
	private Player player1;
	private Player player2;
	
	private ChangeableText player1ScoreText;
	private ChangeableText player2ScoreText;
	private Sound scoreSound;
	private final PongActivity activity;

	public ScoreBoard(Player player1, Player player2, Display display, Font font, PongActivity activity) {
		this.player1 = player1;
		this.player2 = player2;
		this.activity = activity;
		int padding = display.getWidth() / 80;
		player1ScoreText = new ChangeableText(display.getWidth() / 2 + (font.getLineHeight() * 10) / 20 + padding, padding, font, "0");
		player2ScoreText = new ChangeableText(display.getWidth() / 2 - (font.getLineHeight() * 30) / 20, padding, font, "0");
	}
	
	public void playerOneGoal() {
		player1.increaseScore();
		if (player1.getScore() > MAX_POINTS) gameOver(1);
		else player1ScoreText.setText("" + player1.getScore());
		
	}
	
	public void playerTwoGoal() {
		player2.increaseScore();
		if (player2.getScore() > MAX_POINTS) gameOver(2);
		else player2ScoreText.setText("" + player2.getScore());
	}

	public void attachTo(Scene scene) {
		scene.attachChild(player1ScoreText);
		scene.attachChild(player2ScoreText);
	}

	public void setScoreSound(Sound scoreSound) {
		this.scoreSound = scoreSound;
	}
	
	public void playScoreSound() {
		if (scoreSound != null) {
			scoreSound.play();
		}
	}
	
	private void gameOver(int winner) {
		player1.resetScore();
		player2.resetScore();
		player1ScoreText.setText("" + player1.getScore());
		player2ScoreText.setText("" + player2.getScore());
		
		Intent intent = new Intent();
		intent.putExtra("isNPC", activity.getIntent().getBooleanExtra("isNPC", true));
		intent.putExtra("difficulty", activity.getIntent().getIntExtra("difficulty", 16));
		intent.putExtra("winner", winner);
		activity.setResult(Activity.RESULT_OK, intent);
		activity.finish();
	}
}
