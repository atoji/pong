package com.beyondweb.pong.handlers;

import org.anddev.andengine.engine.handler.IUpdateHandler;

import com.beyondweb.pong.model.Ball;
import com.beyondweb.pong.model.Player;

public class PlayerCollisionHandler implements IUpdateHandler {

	private static final int MAX_SPEED = 1100;
	private final Player player1;
	private final Player player2;
	private final Ball ball;

	public PlayerCollisionHandler(Player player1, Player player2, Ball ball) {
		this.player1 = player1;
		this.player2 = player2;
		this.ball = ball;
	}
	
	@Override
	public void onUpdate(float pSecondsElapsed) {
		if (player1.collidesWith(ball)) {
			setNewSpeedOnCollisionWith(player1);
		} 
		else if (player2.collidesWith(ball)) {
			setNewSpeedOnCollisionWith(player2);
		}
	}

	private void setNewSpeedOnCollisionWith(Player player) {
		setNewYSpeedOnCollisionWith(player);
		setNewXSpeedOnCollisionWith(player);
	}

	private void setNewXSpeedOnCollisionWith(Player player) {
		int signal = (player == player1 ? -1 : 1);
		if (Math.abs(ball.getVelocityX()) < MAX_SPEED) {
			ball.setVelocityX(signal * Math.abs(ball.getVelocityX()) * 11 / 10);
		} else {
			ball.setVelocityX(signal * Math.abs(ball.getVelocityX()));
		}
		ball.playPlayerBumpSound();
	}

	private void setNewYSpeedOnCollisionWith(Player player) {
		float newYSpeed = Math.abs((player.getY() + ball.getWidth() * 2) - ball.getY()) * 6;
		if (player.getY() + ball.getWidth() * 2 < ball.getY()) {
			ball.setVelocityY(newYSpeed);
		} else {
			ball.setVelocityY(-newYSpeed);
		}
	}

	@Override
	public void reset() {
	}

}
