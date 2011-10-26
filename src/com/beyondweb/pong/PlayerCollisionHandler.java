package com.beyondweb.pong;

import org.anddev.andengine.engine.handler.IUpdateHandler;

public class PlayerCollisionHandler implements IUpdateHandler {

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
			if (player1.getY() + ball.getWidth() * 2 < ball.getY()) {
				ball.setVelocityY(Math.abs((player1.getY() + ball.getWidth() * 2) - ball.getY()) * 6);
			} else {
				ball.setVelocityY(-Math.abs((player1.getY() + ball.getWidth() * 2) - ball.getY()) * 6);
			}
			ball.setVelocityX(Math.abs(ball.getVelocityX()) + 20);
		} else if (player2.collidesWith(ball)) {
			if (player2.getY() + ball.getHeight() * 2 < ball.getY()) {
				ball.setVelocityY(Math.abs((player2.getY() + ball.getHeight() * 2) - ball.getY()) * 6);
			} else {
				ball.setVelocityY(-Math.abs((player2.getY() + ball.getHeight() * 2) - ball.getY()) * 6);
			}
			ball.setVelocityX(-Math.abs(ball.getVelocityX()) - 20);
		}
	}

	@Override
	public void reset() {
	}

}
