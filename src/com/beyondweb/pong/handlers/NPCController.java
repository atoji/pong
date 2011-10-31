package com.beyondweb.pong.handlers;

import org.anddev.andengine.engine.handler.IUpdateHandler;

import com.beyondweb.pong.model.Ball;
import com.beyondweb.pong.model.Player;

public class NPCController implements IUpdateHandler {

	private final Player player;
	private final Ball ball;
	private final int difficulty;

	public NPCController(Player player, Ball ball, int difficulty) {
		this.player = player;
		this.ball = ball;
		this.difficulty = difficulty;
	}

	@Override
	public void onUpdate(float pSecondsElapsed) {
		float distance = Math.abs(player.getY() + player.getHeight() / 2 - ball.getY());
		if (player.getY() + player.getHeight() / 2 > ball.getY()) {
			player.setPosition(player.getX(), player.getY() - distance / difficulty);
		} else {
			player.setPosition(player.getX(), player.getY() + distance / difficulty);
		}
	}

	@Override
	public void reset() {
	}

}
