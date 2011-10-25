package com.beyondweb.pong;

import org.anddev.andengine.engine.Engine;
import org.anddev.andengine.engine.camera.Camera;
import org.anddev.andengine.engine.handler.IUpdateHandler;
import org.anddev.andengine.engine.handler.physics.PhysicsHandler;
import org.anddev.andengine.engine.options.EngineOptions;
import org.anddev.andengine.engine.options.EngineOptions.ScreenOrientation;
import org.anddev.andengine.engine.options.resolutionpolicy.RatioResolutionPolicy;
import org.anddev.andengine.entity.primitive.Rectangle;
import org.anddev.andengine.entity.scene.Scene;
import org.anddev.andengine.entity.scene.Scene.IOnSceneTouchListener;
import org.anddev.andengine.extension.input.touch.controller.MultiTouchController;
import org.anddev.andengine.extension.input.touch.exception.MultiTouchException;
import org.anddev.andengine.input.touch.TouchEvent;
import org.anddev.andengine.ui.activity.BaseGameActivity;

import android.view.Display;


public class PongActivity extends BaseGameActivity {

	private Camera camera;
	private int CAMERA_WIDTH;
	private int CAMERA_HEIGHT;
	private Rectangle ball;
	private PhysicsHandler physicsHandler;
	private int BALL_RADIUS = 40;
	private int padding = BALL_RADIUS * 2;
	private Rectangle player1;
	private Rectangle player2;

	@Override
	public Engine onLoadEngine() {
		final Display display = getWindowManager().getDefaultDisplay();
	    CAMERA_WIDTH = display.getWidth();
	    CAMERA_HEIGHT = display.getHeight();
	 
	    this.camera = new Camera(0, 0, CAMERA_WIDTH, CAMERA_HEIGHT);
	    
	    Engine engine = new Engine(new EngineOptions(true, ScreenOrientation.LANDSCAPE, new RatioResolutionPolicy(CAMERA_WIDTH, CAMERA_HEIGHT), this.camera));
	    try {
			engine.setTouchController(new MultiTouchController());
		} catch (MultiTouchException e) {
			e.printStackTrace();
		}
	    
		return engine;
	}

	@Override
	public void onLoadResources() {
	}
	
	@Override
	public Scene onLoadScene() {
        Scene scene = new Scene();
        
        scene.registerUpdateHandler(new IUpdateHandler() {
			@Override
			public void reset() {
			}
			
			@Override
			public void onUpdate(float pSecondsElapsed) {
				if (ball.getX() < 0) {
					ball.setPosition(0, ball.getY());
					physicsHandler.setVelocityX(Math.abs(physicsHandler.getVelocityX()));
				}
				else if (ball.getX() > CAMERA_WIDTH - BALL_RADIUS) {
					physicsHandler.setVelocityX(-Math.abs(physicsHandler.getVelocityX()));
				}
					
				if (ball.getY() < 0) {
					ball.setPosition(ball.getX(), 0);
					physicsHandler.setVelocityY(Math.abs(physicsHandler.getVelocityY()));
				} 
				else if (ball.getY() > CAMERA_HEIGHT - BALL_RADIUS) {
					physicsHandler.setVelocityY(-Math.abs(physicsHandler.getVelocityY()));
				}
				
				if (player1.collidesWith(ball)) {
					if (player1.getY() + BALL_RADIUS * 2 < ball.getY()) {
						physicsHandler.setVelocityY(Math.abs((player1.getY() + BALL_RADIUS * 2) - ball.getY()) * 6);
					} else {
						physicsHandler.setVelocityY(-Math.abs((player1.getY() + BALL_RADIUS * 2) - ball.getY()) * 6);
					}
					physicsHandler.setVelocityX(Math.abs(physicsHandler.getVelocityX()));
				} else if (player2.collidesWith(ball)) {
					if (player2.getY() + BALL_RADIUS * 2 < ball.getY()) {
						physicsHandler.setVelocityY(Math.abs((player2.getY() + BALL_RADIUS * 2) - ball.getY()) * 6);
					} else {
						physicsHandler.setVelocityY(-Math.abs((player2.getY() + BALL_RADIUS * 2) - ball.getY()) * 6);
					}
					physicsHandler.setVelocityX(-Math.abs(physicsHandler.getVelocityX()));
				}
			}
		});
        
		ball = new Rectangle(0, 0, BALL_RADIUS, BALL_RADIUS);
        physicsHandler = new PhysicsHandler(ball);
        physicsHandler.setVelocity(250, 250);
        ball.registerUpdateHandler(physicsHandler);
        scene.attachChild(ball);
        
		player1 = new Rectangle(padding, 100, BALL_RADIUS, BALL_RADIUS * 4);
        scene.attachChild(player1);
        
        player2 = new Rectangle(CAMERA_WIDTH - BALL_RADIUS - padding, 100, BALL_RADIUS, BALL_RADIUS * 4);
        scene.attachChild(player2);
        
        scene.setOnAreaTouchTraversalFrontToBack();
        scene.setTouchAreaBindingEnabled(true);
        scene.registerTouchArea(player1);
        scene.registerTouchArea(player2);
        
        scene.setOnSceneTouchListener(new IOnSceneTouchListener() {
			
			@Override
			public boolean onSceneTouchEvent(Scene pScene, TouchEvent pSceneTouchEvent) {
				if (pSceneTouchEvent.getX() < CAMERA_WIDTH / 2) {
					player1.setPosition(player1.getX(), pSceneTouchEvent.getY() - BALL_RADIUS * 2);
				} else {
					player2.setPosition(player2.getX(), pSceneTouchEvent.getY() - BALL_RADIUS * 2);
				}
				return true;
			}
		});
        
        return scene;
	}

	@Override
	public void onLoadComplete() {
	}
	
}
