package com.beyondweb.pong.model;

import org.anddev.andengine.audio.sound.Sound;
import org.anddev.andengine.engine.handler.physics.PhysicsHandler;
import org.anddev.andengine.entity.primitive.Rectangle;
import org.anddev.andengine.entity.scene.Scene;

import android.view.Display;

public class Ball{

	private PhysicsHandler physicsHandler;
	private Rectangle rectangle;
	private Display display;
	private Sound playerBumpSound;
	private Sound wallBumpSound;
	private int startSpeed;
	private int radius;
	
	public Ball(Display display) {
		this.radius = display.getWidth() / 42;
		this.startSpeed = display.getWidth() / 3;
		this.display = display;
		this.rectangle = new Rectangle(display.getWidth() / 2, display.getHeight() / 2, radius, radius);
		this.physicsHandler = new PhysicsHandler(rectangle);
		this.physicsHandler.setVelocity(startSpeed, 0);
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

	public void setVelocityX(float vx) {
		physicsHandler.setVelocityX(vx);
	}

	public void setVelocityY(float vy) {
		physicsHandler.setVelocityY(vy);
	}

	public float getWidth() {
		return rectangle.getWidth();
	}

	public float getHeight() {
		return rectangle.getHeight();
	}

	public void reset() {
		if (getX() < display.getWidth() / 2) { 
			setVelocityX(startSpeed);
		} else {
			setVelocityX(-startSpeed);
		}
		setVelocityY(0);
		setPosition(display.getWidth() / 2, display.getHeight() / 2);
	}
	
	public void setPlayerBumpSound(Sound sound) {
		this.playerBumpSound = sound;
	}
	
	public void playPlayerBumpSound() {
		if (playerBumpSound != null) {
			playerBumpSound.play();
		}
	}

	public void setWallBumpSound(Sound bumpSound) {
		this.wallBumpSound = bumpSound;
	}
	
	public void playWallBumpSound() {
		if (wallBumpSound != null) {
			wallBumpSound.play();
		}
	}
}
