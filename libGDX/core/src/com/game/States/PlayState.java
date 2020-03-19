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
import com.game.Managers.GameInputProcessor;
import com.game.Managers.GameStateManager;
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

    public GameInputProcessor inputProcessor;
    public PlayState(GameStateManager gsm)
    {
        super(gsm);
    }

    @Override
    public void init() {
        sb = new SpriteBatch();
        playerTexture = new Texture(Gdx.files.internal("mario.png"));
        player = new Player(playerTexture,10,100,WIDTH/2,HEIGHT/2);
       // player.sprite.setScale(.2f);
        inputProcessor = new GameInputProcessor(player,gsm);
        currentRoom = new Room("maps/map2.tmx");
        cam = game.cam;
        WIDTH = game.WIDTH;
        HEIGHT = game.HEIGHT;
        mapLoader = new TmxMapLoader();
        map = mapLoader.load(currentRoom.mapName);
        renderer = new OrthogonalTiledMapRenderer(map);
    }

    @Override
    public void update(float dt) {
        handleInput(dt);
        //player.sprite.setPosition(player.posX,player.posY);
        renderer.setView(cam);
        cam.update();
        renderer.setView(cam);

    }

    @Override
    public void draw() {
        sb.setProjectionMatrix(escapeGame.cam.combined);
        renderer.render();
        sb.begin();
        player.sprite.setScale(0.1f);
        player.sprite.draw(sb);
        //sb.draw(playerTexture,player.posX,player.posY);
        sb.end();
    }

    @Override
    public void handleInput(float dt) {
        inputProcessor.movePlayer(dt);
    }

    @Override
    public void dispose() {
        sb.dispose();
        renderer.dispose();
        playerTexture.dispose();
    }
}
