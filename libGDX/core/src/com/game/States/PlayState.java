package com.game.States;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.game.Entities.Enemy;
import com.game.Entities.GreenBlob;
import com.game.Entities.Player;
import com.game.Entities.Room;
import com.game.Managers.*;
import com.game.main.escapeGame;

public class PlayState extends GameState{

    private SpriteBatch sb;
    public static OrthographicCamera cam;
    public int WIDTH = 800;
    public int HEIGHT = 480;
    private Room currentRoom;
    private Texture playerTexture;
    private Player player;
    private Texture enemyTexture;
    private Enemy enemy;
    private Preferences prefs;

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
        enemy = new GreenBlob(640, HEIGHT/2);
        inputProcessor = new GameInputProcessor(player,this.gsm,game);
        String[] maps = {"maps/UP.tmx","maps/DOWN.tmx"};
        dungeonMapManager = new DungeonMapManager(maps,5,5,player);//5x5 dungeon of maps.
        currentRoom = dungeonMapManager.getCurrentRoom();
        mapManager = new TiledMapManager(currentRoom.mapName,game,player);
        cam = game.cam;
        HUD = new UI(player,cam);
        prefs = Gdx.app.getPreferences("GameStorage");
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
        saveHighScore();
        sb.dispose();
        playerTexture.dispose();
        enemyTexture.dispose();
        mapManager.dispose();
        HUD.dispose();
    }


    public void saveHighScore()
    {
        int currentScore = player.getScore();
        int highScore = prefs.getInteger("highScore");

        if(currentScore > highScore){
            prefs.putInteger("highScore",currentScore);
            prefs.putInteger("Rooms Visited", player.getRoomsVisited());
            prefs.putInteger("Enemies Defeated", player.getEnemiesDefeated());
            prefs.flush();
        }

    }
    //GameStateManager gsm,GameState currentGameState,Player player, Enemy[] enemies, UI HUD, TiledMapManager mapManager
    public TiledMapManager getmapManager(){
        return mapManager;
    }

    public Enemy[] getEnemies(){
        return currentRoom.getEnemies();
    }
    public Player getPlayer()
    {
        return player;
    }
    public UI getHUD()
    {
        return HUD;
    }
}
