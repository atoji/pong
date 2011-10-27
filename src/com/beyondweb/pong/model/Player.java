package com.beyondweb.pong.model;

import org.anddev.andengine.entity.primitive.Rectangle;
import org.anddev.andengine.entity.scene.Scene;

public abstract class Player {

	private Rectangle rectangle;
	private int score;

	public float getX() {
		return getRectangle().getX();
	}

	public float getY() {
		return getRectangle().getY();
	}

	public boolean collidesWith(Ball ball) {
		return getRectangle().collidesWith(ball.getRectangle());
	}

	public float getHeight() {
		return getRectangle().getHeight();
	}

	public void setPosition(float x, float y) {
		getRectangle().setPosition(x, y);
	}

	public void attachTo(Scene scene) {
		scene.attachChild(getRectangle());
	}

	public Rectangle getRectangle() {
		return rectangle;
	}

	public void setRectangle(Rectangle rectangle) {
		this.rectangle = rectangle;
	}

	public int getScore() {
		return score;
	}

	public void increaseScore() {
		this.score++;
	}

	public void resetScore() {
		this.score = 0;
	}

}
