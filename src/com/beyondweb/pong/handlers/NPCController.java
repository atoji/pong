package com.beyondweb.pong.handlers;

import org.anddev.andengine.engine.handler.IUpdateHandler;

import com.beyondweb.pong.model.Ball;
import com.beyondweb.pong.model.Player;

public class NPCController implements IUpdateHandler {

	private final Player player;
	private final Ball ball;
	private final int dificulty;

	public NPCController(Player player, Ball ball, int dificulty) {
		this.player = player;
		this.ball = ball;
		this.dificulty = dificulty;
	}

	@Override
	public void onUpdate(float pSecondsElapsed) {
		float distance = Math.abs(player.getY() + player.getHeight() / 2 - ball.getY());
		if (player.getY() + player.getHeight() / 2 > ball.getY()) {
			player.setPosition(player.getX(), player.getY() - distance / dificulty);
		} else {
			player.setPosition(player.getX(), player.getY() + distance / dificulty);
		}
	}

	@Override
	public void reset() {
	}

}
