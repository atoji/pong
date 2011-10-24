package com.beyondweb.pong;

import org.anddev.andengine.engine.Engine;
import org.anddev.andengine.engine.camera.Camera;
import org.anddev.andengine.engine.options.EngineOptions;
import org.anddev.andengine.engine.options.EngineOptions.ScreenOrientation;
import org.anddev.andengine.engine.options.resolutionpolicy.RatioResolutionPolicy;
import org.anddev.andengine.entity.scene.Scene;
import org.anddev.andengine.entity.scene.background.ColorBackground;
import org.anddev.andengine.entity.util.FPSLogger;
import org.anddev.andengine.ui.activity.BaseGameActivity;

import android.view.Display;


public class PongActivity extends BaseGameActivity {

	private Camera camera;
	private int CAMERA_WIDTH;
	private int CAMERA_HEIGHT;

	@Override
	public Engine onLoadEngine() {
		final Display display = getWindowManager().getDefaultDisplay();
	    CAMERA_WIDTH = display.getWidth();
	    CAMERA_HEIGHT = display.getHeight();
	 
	    this.camera = new Camera(0, 0, CAMERA_WIDTH, CAMERA_HEIGHT);
	    return new Engine(new EngineOptions(true, ScreenOrientation.LANDSCAPE, 
	        new RatioResolutionPolicy(CAMERA_WIDTH, CAMERA_HEIGHT), this.camera));
	}

	@Override
	public void onLoadResources() {
	}

	@Override
	public Scene onLoadScene() {
		this.mEngine.registerUpdateHandler(new FPSLogger());

        final Scene scene = new Scene();
        scene.setBackground(new ColorBackground(0.09804f, 0.6274f, 0.8784f));

        return scene;
	}

	@Override
	public void onLoadComplete() {
	}

}
