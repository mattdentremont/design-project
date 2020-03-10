package com.game.main;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Intersector;
import com.game.Managers.GameInputProcessor;
import com.game.Managers.GameStateManager;


public class escapeGame extends ApplicationAdapter {

	public static int WIDTH;
	public static int HEIGHT;

	public static OrthographicCamera cam;
	private GameStateManager gsm;

	SpriteBatch batch;


	@Override
	public void create() {
		WIDTH = 1920;
		HEIGHT = 1080;
		cam = new OrthographicCamera(WIDTH, HEIGHT);
		cam.translate(WIDTH / 2, HEIGHT / 2);
		cam.update();

		batch = new SpriteBatch();
		gsm = new GameStateManager();
	}

	@Override
	public void render() {

		Gdx.gl.glClearColor(1, 0, 0, 1);
		//Clear Screen to black then open textures
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		gsm.update(Gdx.graphics.getDeltaTime());
		gsm.draw();
	}
		@Override
		public void dispose () {
			batch.dispose();
		}
}
