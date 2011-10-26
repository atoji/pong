package com.beyondweb.pong;

import org.anddev.andengine.engine.Engine;
import org.anddev.andengine.engine.camera.Camera;
import org.anddev.andengine.engine.options.EngineOptions;
import org.anddev.andengine.engine.options.EngineOptions.ScreenOrientation;
import org.anddev.andengine.engine.options.resolutionpolicy.RatioResolutionPolicy;
import org.anddev.andengine.entity.scene.Scene;
import org.anddev.andengine.extension.input.touch.controller.MultiTouchController;
import org.anddev.andengine.extension.input.touch.exception.MultiTouchException;
import org.anddev.andengine.ui.activity.BaseGameActivity;

import android.view.Display;


public class PongActivity extends BaseGameActivity {

	private Camera camera;
	private Ball ball;
	private Display display;
	private Player player1;
	private Player player2;
	private ScoreBoard scoreBoard;

	@Override
	public Engine onLoadEngine() {
		display = getWindowManager().getDefaultDisplay();
	    this.camera = new Camera(0, 0, display.getWidth(), display.getHeight());
	    
	    RatioResolutionPolicy resolutionPolicy = new RatioResolutionPolicy(display.getWidth(), display.getHeight());
		EngineOptions options = new EngineOptions(true, ScreenOrientation.LANDSCAPE, resolutionPolicy, this.camera);
		Engine engine = new Engine(options);
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
        
        new StadiumBuilder(display).buildOn(scene);
        
        ball = new Ball(display);
        ball.attachTo(scene);

        player1 = new Player1(display);
		player1.attachTo(scene);
        
        player2 = new Player2(display);
        player2.attachTo(scene);
        
        scoreBoard = new ScoreBoard(player1, player2);
        
        scene.setOnAreaTouchTraversalFrontToBack();
        scene.setTouchAreaBindingEnabled(true);

        scene.setOnSceneTouchListener(new PlayerControllerTouchListener(player1, player2, display));
		scene.registerUpdateHandler(new BordersCollisionHandler(ball, display, scoreBoard));
        scene.registerUpdateHandler(new PlayerCollisionHandler(player1, player2, ball));
        
        return scene;
	}

	@Override
	public void onLoadComplete() {
	}
	
}
