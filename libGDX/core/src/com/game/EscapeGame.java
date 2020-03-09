package com.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
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



public class escapeGame extends ApplicationAdapter {
	Texture img;
	public static int WIDTH;
	public static int HEIGHT;
	public static OrthographicCamera cam;
	private OrthogonalTiledMapRenderer tmr;
	private TiledMap map;

	Player player;
	Enemy enemy;
	SpriteBatch batch;
	Texture mario;
	Texture motion;
	Texture lvl;
	Texture dead;
	Move move;
	BitmapFont font;


	@Override
	public void create () {
		WIDTH = 1920;
		HEIGHT=1080;

		font = new BitmapFont();
		font.setColor(Color.CYAN);
		batch = new SpriteBatch();
		mario = new Texture("mario.png");
		motion = new Texture("jamil.jpg");
		dead = new Texture("deathscreen.png");
		lvl = new Texture("tilted.jpg");
		player = new Player(mario,100);
		enemy = new Enemy(motion,1);
		move = new Move(player.sprite,WIDTH,HEIGHT);
		player.sprite.setPosition(WIDTH/2 - player.sprite.getWidth()/2, HEIGHT/2 - player.sprite.getWidth()/2);
		enemy.sprite.setPosition(1000,700);
		player.sprite.setBounds(50,50,50,50);

		map = new TmxMapLoader().load("maps/map1.tmx");
		tmr = new OrthogonalTiledMapRenderer(map);



	}

	@Override
	public void render () {

		Gdx.gl.glClearColor(1, 0, 0, 1);

		//Clear Screen to black then open textures
		Gdx.gl.glClearColor(0, 0, 0, 1);

		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		move.movePlayer();
		if(Intersector.overlaps(player.sprite.getBoundingRectangle(),enemy.sprite.getBoundingRectangle()))
		{
			player.damage(enemy.damage);
			player.sprite.setColor(Color.RED);
		}

		batch.begin();
		batch.draw(lvl,0,0);
		if(player.isDead)
		{
			font.setColor(Color.RED);
			batch.draw(dead,0,0);

		}
		player.sprite.draw(batch);
		enemy.sprite.draw(batch);
		font.draw(batch,"X:" + player.sprite.getX() + ", Y:" + player.sprite.getY(),500,500);
		batch.end();
	}
	
	@Override
	public void dispose () {
		batch.dispose();

		img.dispose();

		mario.dispose();
		motion.dispose();
		font.dispose();
		tmr.dispose();
		map.dispose();
	}
}