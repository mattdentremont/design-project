package com.game.main;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.game.Managers.GameStateManager;


//Main game class! This class' functions are the highest level functions that are constantly called!
public class escapeGame extends ApplicationAdapter {

	public static int WIDTH;//resolution x
	public static int HEIGHT;//resolution y

	public static OrthographicCamera cam;//game camera config.
	private GameStateManager gsm;//game state manager to manage screens at all times.


	//Create is called when the game is started up:
	@Override
	public void create() {
		WIDTH = 800;
		HEIGHT = 480;
		cam = new OrthographicCamera(WIDTH, HEIGHT);
		cam.translate(WIDTH / 2, HEIGHT / 2);
		cam.update();
		gsm = new GameStateManager(this);
	}

	//render is constantly called (loops while game is running).
	@Override
	public void render() {

		Gdx.gl.glClearColor(1, 0, 0, 1);
		//Clear Screen to black then open textures
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		//Call gsm.update.It's parameter is a float value in seconds that is the time elapsed since the last call to this function.
		gsm.update(Gdx.graphics.getDeltaTime());
		gsm.draw();//draw game screen.
	}
		@Override
		public void dispose () {
		}
}
