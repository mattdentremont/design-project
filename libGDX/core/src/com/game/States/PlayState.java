package com.game.States;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.game.Entities.Player;
import com.game.Entities.Room;
import com.game.Managers.DungeonMapManager;
import com.game.Managers.GameStateManager;
import com.game.main.escapeGame;

public class PlayState extends GameState{

    //private DungeonMapManager dm;
    private SpriteBatch sb;
    public static OrthographicCamera cam;
    public int WIDTH = 1920;
    public int HEIGHT = 1080;
    private Room currentRoom;
    private TmxMapLoader mapLoader;
    private TiledMap map;
    private OrthogonalTiledMapRenderer renderer;
    private Texture playerTexture;
    private Player player;

    public PlayState(GameStateManager gsm)
    {
        super(gsm);
    }

    @Override
    public void init() {
        sb = new SpriteBatch();
        playerTexture = new Texture(Gdx.files.internal("mario.png"));
        player = new Player(playerTexture,10,100);
        currentRoom = new Room("maps/map1.tmx");
        cam = new OrthographicCamera(WIDTH, HEIGHT);
        cam.translate(WIDTH / 2, HEIGHT / 2);
        cam.update();
        mapLoader = new TmxMapLoader();
        map = mapLoader.load(currentRoom.mapName);
        renderer = new OrthogonalTiledMapRenderer(map);
        renderer.setView(cam);
    }

    @Override
    public void update(float dt) {
        handleInput();
        renderer.setView(cam);
        cam.update();
        //update cam?
        //cam.update();
        //renderer.setView(cam);

    }

    @Override
    public void draw() {
        sb.setProjectionMatrix(escapeGame.cam.combined);
        sb.begin();
        renderer.render();
        sb.end();
    }

    @Override
    public void handleInput() {

    }

    @Override
    public void dispose() {
        sb.dispose();
        renderer.dispose();
        playerTexture.dispose();
    }
}
