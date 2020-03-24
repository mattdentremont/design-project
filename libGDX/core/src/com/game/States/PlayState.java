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
import com.game.Entities.Enemy;
import com.game.Entities.GreenBlob;
import com.game.Entities.Player;
import com.game.Entities.Room;
import com.game.Managers.*;
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
    private Texture enemyTexture;
    private Enemy enemy;
    private String roomPath;

    public DungeonMapManager dungeonMapManager;
    public GameInputProcessor inputProcessor;
    public TiledMapManager mapManager;
    public UI HUD;
    public PlayState(GameStateManager gsm)
    {
        super(gsm);
    }

    @Override
    public void init() {
        sb = new SpriteBatch();
        WIDTH = game.WIDTH;
        HEIGHT = game.HEIGHT;
        playerTexture = new Texture(Gdx.files.internal("Protag.png"));
        player = new Player(playerTexture,10,100,WIDTH/2,HEIGHT/2);
        enemyTexture = new Texture(Gdx.files.internal("BobbyBlob.png"));
        enemy = new GreenBlob(enemyTexture, 10, 20, 640, HEIGHT/2);
        inputProcessor = new GameInputProcessor(player,this.gsm,game);
        String[] maps = {"maps/UP.tmx","maps/DOWN.tmx"};
        dungeonMapManager = new DungeonMapManager(maps,5,5,player);//5x5 dungeon of maps.
        currentRoom = dungeonMapManager.getCurrentRoom();
        mapManager = new TiledMapManager(currentRoom.mapName,game,player);
        cam = game.cam;
        HUD = new UI(player,cam);
    }

    @Override
    public void update(float dt) {
        enemy.move(player,enemy.movementSpeed,dt);
        handleInput(dt);
        cam.update();
        mapManager.updateCam();
    }

    @Override
    public void draw() {
        sb.setProjectionMatrix(escapeGame.cam.combined);
        mapManager.render();
        mapManager.checkDoors(dungeonMapManager);
        sb.begin();
        HUD.draw(sb);
        player.sprite.draw(sb);
        enemy.sprite.draw(sb);
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
        enemyTexture.dispose();
        mapManager.dispose();
        HUD.dispose();
    }
}
