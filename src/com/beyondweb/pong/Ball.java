package com.beyondweb.pong;

import org.anddev.andengine.engine.handler.physics.PhysicsHandler;
import org.anddev.andengine.entity.primitive.Rectangle;
import org.anddev.andengine.entity.scene.Scene;

import android.view.Display;

public class Ball{

	private PhysicsHandler physicsHandler;
	private Rectangle rectangle;
	private Display display;
	
	public Ball(Display display) {
		this.display = display;
		this.rectangle = new Rectangle(display.getWidth() / 2, display.getHeight() / 2, 20, 20);
		this.physicsHandler = new PhysicsHandler(rectangle);
		this.physicsHandler.setVelocity(250, 250);
		this.rectangle.registerUpdateHandler(physicsHandler);
	}

	public void attachTo(Scene scene) {
		scene.attachChild(rectangle);
	}
	
	public void setPosition(float x, float y) {
		rectangle.setPosition(x, y);
	}
	
	public float getX() {
		return rectangle.getX();
	}
	
	public float getY() {
		return rectangle.getY();
	}
	
	public Rectangle getRectangle() {
		return rectangle;
	}

	public float getVelocityX() {
		return physicsHandler.getVelocityX();
	}

	public float getVelocityY() {
		return physicsHandler.getVelocityY();
	}

	public void setVelocityX(float d) {
		physicsHandler.setVelocityX(d);
	}

	public void setVelocityY(float abs) {
		physicsHandler.setVelocityY(abs);
	}

	public float getWidth() {
		return rectangle.getWidth();
	}

	public float getHeight() {
		return rectangle.getHeight();
	}

	public void reset() {
		if (getX() < display.getWidth() / 2) { 
			setVelocityX(250);
		} else {
			setVelocityX(-250);
		}
		setPosition(display.getWidth() / 2, display.getHeight() / 2);
	}
}
