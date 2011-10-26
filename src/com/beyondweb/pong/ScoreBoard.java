package com.beyondweb.pong;

import org.anddev.andengine.audio.sound.Sound;
import org.anddev.andengine.entity.scene.Scene;
import org.anddev.andengine.entity.text.ChangeableText;
import org.anddev.andengine.opengl.font.Font;

import android.view.Display;


public class ScoreBoard {

	private Player player1;
	private Player player2;
	
	private ChangeableText player1ScoreText;
	private ChangeableText player2ScoreText;
	private Sound scoreSound;

	public ScoreBoard(Player player1, Player player2, Display display, Font font) {
		this.player1 = player1;
		this.player2 = player2;
		int padding = display.getWidth() / 80;
		player1ScoreText = new ChangeableText(display.getWidth() / 2 - (font.getLineHeight() * 30) / 20, padding, font, "0");
		player2ScoreText = new ChangeableText(display.getWidth() / 2 + (font.getLineHeight() * 10) / 20 + padding, padding, font, "0");
	}
	
	public void playerOneGoal() {
		player1.increaseScore();
		if (player1.getScore() > 9) gameOver();
		else player1ScoreText.setText("" + player1.getScore());
		
	}
	
	public void playerTwoGoal() {
		player2.increaseScore();
		if (player2.getScore() > 9) gameOver();
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
	
	private void gameOver() {
		player1.resetScore();
		player2.resetScore();
		player1ScoreText.setText("" + player1.getScore());
		player2ScoreText.setText("" + player2.getScore());
	}
}
