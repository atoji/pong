package com.beyondweb.pong;

import org.anddev.andengine.entity.primitive.Rectangle;

import android.view.Display;


public class Player2 extends Player{

	public Player2(Display display) {
		setRectangle(new Rectangle(display.getWidth() - 120, display.getHeight() / 2 - 50, 20, 100));
	}
	
}
