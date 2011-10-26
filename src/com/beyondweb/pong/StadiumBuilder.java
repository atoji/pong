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
		int traceWidth = display.getWidth() / 64;
		scene.attachChild(new Rectangle(0, 0, display.getWidth(), traceWidth));
		scene.attachChild(new Rectangle(0, display.getHeight() - traceWidth, display.getWidth(), traceWidth));
		
		int currentHeight = 0;
		while (currentHeight < display.getHeight()) {
			scene.attachChild(new Rectangle(display.getWidth() / 2, currentHeight, traceWidth, traceWidth));
			currentHeight += traceWidth * 2;
		}
		
	}
	
}
