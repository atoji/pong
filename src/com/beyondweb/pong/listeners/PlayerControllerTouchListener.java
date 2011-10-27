package com.beyondweb.pong.listeners;

import org.anddev.andengine.entity.scene.Scene;
import org.anddev.andengine.entity.scene.Scene.IOnSceneTouchListener;
import org.anddev.andengine.input.touch.TouchEvent;

import com.beyondweb.pong.model.Player;

import android.view.Display;

public class PlayerControllerTouchListener implements IOnSceneTouchListener{

	private final Display display;
	private final Player player1;
	private final Player player2;
	private final boolean isNPC;

	public PlayerControllerTouchListener(Player player1, Player player2, Display display, boolean isNPC) {
		this.player1 = player1;
		this.player2 = player2;
		this.display = display;
		this.isNPC = isNPC;
	}
	
	@Override
	public boolean onSceneTouchEvent(Scene pScene, TouchEvent pSceneTouchEvent) {
		if (pSceneTouchEvent.getX() > display.getWidth() / 2) {
			player1.setPosition(player1.getX(), pSceneTouchEvent.getY() - player1.getHeight() / 2);
		} else if (!isNPC){
			player2.setPosition(player2.getX(), pSceneTouchEvent.getY() - player2.getHeight() / 2);
		}
		return true;
	}

}
