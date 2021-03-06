package com.beyondweb.pong.activities;

import java.io.IOException;

import org.anddev.andengine.audio.sound.Sound;
import org.anddev.andengine.audio.sound.SoundFactory;
import org.anddev.andengine.engine.Engine;
import org.anddev.andengine.engine.camera.Camera;
import org.anddev.andengine.engine.options.EngineOptions;
import org.anddev.andengine.engine.options.EngineOptions.ScreenOrientation;
import org.anddev.andengine.engine.options.resolutionpolicy.RatioResolutionPolicy;
import org.anddev.andengine.entity.scene.Scene;
import org.anddev.andengine.extension.input.touch.controller.MultiTouchController;
import org.anddev.andengine.extension.input.touch.exception.MultiTouchException;
import org.anddev.andengine.opengl.font.Font;
import org.anddev.andengine.opengl.font.FontFactory;
import org.anddev.andengine.opengl.texture.TextureOptions;
import org.anddev.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlas;
import org.anddev.andengine.ui.activity.BaseGameActivity;

import com.beyondweb.pong.R;
import com.beyondweb.pong.handlers.BordersCollisionHandler;
import com.beyondweb.pong.handlers.NPCController;
import com.beyondweb.pong.handlers.PlayerCollisionHandler;
import com.beyondweb.pong.listeners.PlayerControllerTouchListener;
import com.beyondweb.pong.model.Ball;
import com.beyondweb.pong.model.Player;
import com.beyondweb.pong.model.Player2;
import com.beyondweb.pong.model.Player1;
import com.beyondweb.pong.model.ScoreBoard;
import com.beyondweb.pong.model.StadiumBuilder;
import com.beyondweb.pong.util.Constants;

import android.graphics.Color;
import android.util.Log;
import android.view.Display;
import android.widget.Toast;


public class PongActivity extends BaseGameActivity {

	private Camera camera;
	private Ball ball;
	private Display display;
	private Player player2;
	private Player player1;
	private ScoreBoard scoreBoard;
	private BitmapTextureAtlas atariFontTexture;
	private Font atariFont;
	private Sound bumpSound;
	private Sound playerSound;
	private Sound scoreSound;

	@Override
	public Engine onLoadEngine() {
		display = getWindowManager().getDefaultDisplay();
	    this.camera = new Camera(0, 0, display.getWidth(), display.getHeight());
	    
	    RatioResolutionPolicy resolutionPolicy = new RatioResolutionPolicy(display.getWidth(), display.getHeight());
		EngineOptions options = new EngineOptions(true, ScreenOrientation.LANDSCAPE, resolutionPolicy, this.camera).setNeedsSound(true);
		Engine engine = new Engine(options);
	    try {
			engine.setTouchController(new MultiTouchController());
		} catch (MultiTouchException e) {
			Toast.makeText(this, R.string.not_support_multitouch, Toast.LENGTH_SHORT).show();
			finish();
		}
		return engine;
	}

	@Override
	public void onLoadResources() {
		this.atariFontTexture = new BitmapTextureAtlas(256, 256, TextureOptions.BILINEAR_PREMULTIPLYALPHA);
		FontFactory.setAssetBasePath("font/");
		this.atariFont = FontFactory.createFromAsset(this.atariFontTexture, this, "atari.ttf", display.getWidth() / 16, true, Color.WHITE);
		
		SoundFactory.setAssetBasePath("mfx/");
		try {
			this.bumpSound = SoundFactory.createSoundFromAsset(this.mEngine.getSoundManager(), this, "bump.ogg");
			this.playerSound = SoundFactory.createSoundFromAsset(this.mEngine.getSoundManager(), this, "player.ogg");
			this.scoreSound = SoundFactory.createSoundFromAsset(this.mEngine.getSoundManager(), this, "score.ogg");
		} catch (IOException e) {
			Log.e("Pong", "Sound error");
		}

		this.mEngine.getTextureManager().loadTextures(this.atariFontTexture);
		this.getFontManager().loadFonts(this.atariFont);
	}
	
	@Override
	public Scene onLoadScene() {
        Scene scene = new Scene();
        
        new StadiumBuilder(display).buildOn(scene);
        
        ball = new Ball(display);
        ball.setPlayerBumpSound(playerSound);
        ball.setWallBumpSound(bumpSound);
        ball.attachTo(scene);

        player1 = new Player1(display);
        player1.attachTo(scene);
        
        player2 = new Player2(display);
        player2.attachTo(scene);
        
        scoreBoard = new ScoreBoard(player1, player2, display, atariFont, this);
        scoreBoard.setScoreSound(scoreSound);
        scoreBoard.attachTo(scene);
        
        scene.setOnAreaTouchTraversalFrontToBack();
        scene.setTouchAreaBindingEnabled(true);

        boolean isNPC = getIntent().getExtras().getBoolean("isNPC");
		scene.setOnSceneTouchListener(new PlayerControllerTouchListener(player1, player2, display, isNPC));
		if (isNPC) {
			scene.registerUpdateHandler(new NPCController(player2, ball, getIntent().getIntExtra("difficulty", Constants.DIFFICULTY_EASY)));
		}
		scene.registerUpdateHandler(new BordersCollisionHandler(ball, display, scoreBoard));
        scene.registerUpdateHandler(new PlayerCollisionHandler(player1, player2, ball));
        
        return scene;
	}

	@Override
	public void onLoadComplete() {
	}
	
}
