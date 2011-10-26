package com.beyondweb.pong;

import org.anddev.andengine.entity.primitive.Rectangle;

import android.view.Display;


public class Player2 extends Player{

	public Player2(Display display) {
		int width = display.getWidth() / 42;
		int height = display.getHeight() / 5;
		int x = display.getWidth() - display.getWidth() / 8 - width;
		int y = display.getHeight() / 2 - height / 2;
		
		setRectangle(new Rectangle(x, y, width, height));
	}
	
}
