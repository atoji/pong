package com.beyondweb.pong;

import org.anddev.andengine.engine.handler.IUpdateHandler;

import android.view.Display;

public class BordersCollisionHandler implements IUpdateHandler {
	
	private final Ball ball;
	private final Display display;
	private final ScoreBoard scoreBoard;

	public BordersCollisionHandler(Ball ball, Display display, ScoreBoard scoreBoard) {
		this.ball = ball;
		this.display = display;
		this.scoreBoard = scoreBoard;
	}

	@Override
	public void onUpdate(float pSecondsElapsed) {
		if (ball.getX() < 0) {
			scoreBoard.playerOneGoal();
			scoreBoard.playScoreSound();
			ball.reset();
		}
		else if (ball.getX() > display.getWidth() - ball.getWidth()) {
			scoreBoard.playerTwoGoal();
			scoreBoard.playScoreSound();
			ball.reset();
		}
			
		if (ball.getY() < 10) {
			ball.setPosition(ball.getX(), 10);
			ball.setVelocityY(Math.abs(ball.getVelocityY()));
			ball.playWallBumpSound();
		} 
		else if (ball.getY() > display.getHeight() - ball.getHeight() - 10) {
			ball.setPosition(ball.getX(), display.getHeight() - ball.getHeight() - 10);
			ball.setVelocityY(-Math.abs(ball.getVelocityY()));
			ball.playWallBumpSound();
		}
	}

	@Override
	public void reset() {
	}

}
