package com.game.States;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.game.Entities.Enemy;
import com.game.Entities.Player;
import com.game.Entities.Room;
import com.game.Managers.*;
import com.game.main.escapeGame;

import java.util.ArrayList;
import java.util.Iterator;

public class PlayState extends GameState{

    private SpriteBatch sb;
    public static OrthographicCamera cam;
    public int WIDTH = 800;
    public int HEIGHT = 480;
    private Room currentRoom;
    private Texture playerTexture;
    private Player player;
    private Preferences prefs;

    public DungeonMapManager dungeonMapManager;
    public GameInputProcessor inputProcessor;
    public TiledMapManager mapManager;
    public UI HUD;
    public PlayState(GameStateManager gsm)
    {
        super(gsm);
    }
    private Sprite menuSprite;
    private Music music;
    private Sound deathSound = Gdx.audio.newSound(Gdx.files.internal("bigOof.mp3"));

    @Override
    public void init() {
        sb = new SpriteBatch();
        WIDTH = game.WIDTH;
        HEIGHT = game.HEIGHT;
        playerTexture = new Texture(Gdx.files.internal("Protag.png"));
        player = new Player(playerTexture,10,100,WIDTH/2,HEIGHT/2);
        String[] maps = {"maps/generic.tmx","maps/satanic.tmx"};
        dungeonMapManager = new DungeonMapManager(maps,25,25,player);//225 dungeon rooms total.
        currentRoom = dungeonMapManager.getCurrentRoom();
        mapManager = new TiledMapManager(currentRoom.mapName,game,player);
        inputProcessor = new GameInputProcessor(player,this.gsm,game,mapManager.getEnemyList());
        cam = game.cam;
        HUD = new UI(player,cam);
        prefs = Gdx.app.getPreferences("GameStorage");
        music = Gdx.audio.newMusic(Gdx.files.internal("menuMusic.mp3"));
        music.setLooping(true);
        music.setVolume(.2f);
        music.play();
    }

    @Override
    public void update(float dt) {
        for (Enemy x :mapManager.getEnemyList()){
            x.move(player,x.movementSpeed,dt);
            x.attack(player, dt);
        }
        handleInput(dt);
        if(player.checkDead()){
            gsm.playerDied(gsm.getCurrentState());
           long deathID =  deathSound.play(1.0f);
        }
        //player.sprite.setTexture(playerTexture);
        ArrayList<Enemy> enemies = mapManager.getEnemyList();
        Iterator<Enemy> iterator = enemies.iterator();
        while(iterator.hasNext()){
            if(iterator.next().checkDead()) {
                iterator.remove();
                player.incrementScore(10);
                player.enemiesDefeated++;
            }
        }
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
        for (Enemy x :mapManager.getEnemyList()){
           x.sprite.draw(sb);
        }
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
        mapManager.dispose();
        HUD.dispose();
        music.dispose();
        deathSound.dispose();
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

    public ArrayList<Enemy> getEnemies(){
        return mapManager.getEnemies();
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
