package com.beyondweb.pong;

import org.anddev.andengine.entity.primitive.Rectangle;
import org.anddev.andengine.entity.scene.Scene;

import android.view.Display;

public class StadiumBuilder {

	private final Display display;

	public StadiumBuilder(Display display) {
		this.display = display;
	}
	
	public void buildOn(Scene scene) {
		scene.attachChild(new Rectangle(0, 0, display.getWidth(), 10));
		scene.attachChild(new Rectangle(0, display.getHeight() - 10, display.getWidth(), 10));
		
		int currentHeight = 0;
		while (currentHeight < display.getHeight()) {
			scene.attachChild(new Rectangle(display.getWidth() / 2, currentHeight, 10, 10));
			currentHeight += 20;
		}
		
	}
	
}
