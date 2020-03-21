package com.game.States;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.MapObjects;
import com.badlogic.gdx.maps.MapProperties;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.objects.TiledMapTileMapObject;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.game.Entities.Player;
import com.game.Entities.Room;
import com.game.Managers.DungeonMapManager;
import com.game.Managers.GameInputProcessor;
import com.game.Managers.GameStateManager;
import com.game.Managers.TiledMapManager;
import com.game.main.escapeGame;

public class PlayState extends GameState{

    private escapeGame game;
    private SpriteBatch sb;
    public static OrthographicCamera cam;
    public int WIDTH = 800;
    public int HEIGHT = 480;
    private Room currentRoom;
    private TmxMapLoader mapLoader;
    private TiledMap map;
    private OrthogonalTiledMapRenderer renderer;
    private Texture playerTexture;
    private Player player;
    private String roomPath;

    public DungeonMapManager dungeonMapManager;
    public GameInputProcessor inputProcessor;
    public TiledMapManager mapManager;
    public PlayState(GameStateManager gsm)
    {
        super(gsm);
    }

    @Override
    public void init() {
        sb = new SpriteBatch();
        playerTexture = new Texture(Gdx.files.internal("Protag.png"));
        player = new Player(playerTexture,10,100,WIDTH/2,HEIGHT/2);
        inputProcessor = new GameInputProcessor(player,this.gsm,game);
        String[] maps = {"maps/UP.tmx","maps/DOWN.tmx"};
        dungeonMapManager = new DungeonMapManager(maps,5,5);//5x5 dungeon of maps.
        currentRoom = dungeonMapManager.getCurrentRoom();
        mapManager = new TiledMapManager(currentRoom.mapName,game,player);
        cam = game.cam;
        WIDTH = game.WIDTH;
        HEIGHT = game.HEIGHT;
    }

    @Override
    public void update(float dt) {
        handleInput(dt);
        cam.update();
        mapManager.updateCam();
    }

    @Override
    public void draw() {
        sb.setProjectionMatrix(escapeGame.cam.combined);
        mapManager.render();
        sb.begin();
        player.sprite.draw(sb);
        sb.end();
    }

    @Override
    public void handleInput(float dt) {
        inputProcessor.movePlayer(dt);
    }

    @Override
    public void dispose() {
        sb.dispose();
        playerTexture.dispose();
        mapManager.dispose();
    }
}
