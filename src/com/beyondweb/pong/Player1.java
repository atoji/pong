package com.beyondweb.pong;

import org.anddev.andengine.entity.primitive.Rectangle;

import android.view.Display;


public class Player1 extends Player {

	public Player1(Display display) {
		setRectangle(new Rectangle(100, display.getHeight() / 2 - 50, 20, 100));
	}

}
