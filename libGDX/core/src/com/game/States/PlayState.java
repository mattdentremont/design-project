package com.game.States;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
//import com.game.Entities.DVDemon;
import com.badlogic.gdx.math.Rectangle;
import com.game.Entities.*;
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

    public Enemy boss;
    public DungeonMapManager dungeonMapManager;
    public GameInputProcessor inputProcessor;
    public TiledMapManager mapManager;
    public UI HUD;
    public PlayState(GameStateManager gsm, String t, float s, int h, int d)
    {
        super(gsm);
        playerConstructor(t, s, h, d);
        init();
        //playerConstructor(t, s, h, d);
    }
    private Sprite menuSprite;
    private Music music;
    private Sound deathSound = Gdx.audio.newSound(Gdx.files.internal("bigOof.mp3"));

    @Override
    public void init() {
        sb = new SpriteBatch();
        WIDTH = game.WIDTH;
        HEIGHT = game.HEIGHT;
        String[] maps = {"maps/generic.tmx","maps/satanic.tmx"};
        dungeonMapManager = new DungeonMapManager(maps,10,10,player);//225 dungeon rooms total.
        currentRoom = dungeonMapManager.getCurrentRoom();
        mapManager = new TiledMapManager(currentRoom.mapName,game,player);
        inputProcessor = new GameInputProcessor(player,this.gsm,game,mapManager.getEnemyList());
        cam = game.cam;
        HUD = new UI(player,cam,inputProcessor);
        prefs = Gdx.app.getPreferences("GameStorage");
        music = Gdx.audio.newMusic(Gdx.files.internal("Doom.mp3"));
        music.setLooping(true);
        music.setVolume(.2f);
        music.play();
    }

    @Override
    public void update(float dt) {
        ArrayList<Enemy> enemies = mapManager.getEnemyList();
        ArrayList<Item> items = mapManager.getItemList();
        Item[] playerInventory = player.getInventory();
        Iterator<Enemy> iterator = enemies.iterator();
        Iterator<Item> iteratorItems = items.iterator();
        Rectangle playerHitBox = player.sprite.getBoundingRectangle();
        Item toPickup = null;

        for (Enemy x :enemies){
            x.move(player,x.movementSpeed,dt);
            x.attack(player, dt);
        }
        for(Item x : items){
            if(playerHitBox.overlaps(x.sprite.getBoundingRectangle())){
                if (x.type == "BEER") {
                    if (playerInventory[0] == null) {
                        toPickup = x;
                    }
                }
                else
                if (playerInventory[1] == null) {
                    toPickup = x;
                }
            }
        }
        if(toPickup != null)
        {
            player.pickUp(toPickup);
        }

        while(iteratorItems.hasNext()){
            if(iteratorItems.next().isPickedUp) {
                iteratorItems.remove();
            }
        }
        while(iterator.hasNext()){
            Enemy x = iterator.next();
            if(x.checkDead()) {
                iterator.remove();
                if(x.isBoss)
                {
                    player.incrementScore(100);
                    player.incrementUltCharge(60);
                }
                else {
                    player.incrementScore(10);
                    player.incrementUltCharge(1);
                }
                player.enemiesDefeated++;
            }

        }
        if(player.checkDead()){
            gsm.playerDied(gsm.getCurrentState());
            deathSound.play(1.0f);
        }
        cam.update();
        mapManager.updateCam();
        handleInput(dt);
    }

    @Override
    public void draw() {
        sb.setProjectionMatrix(escapeGame.cam.combined);
        mapManager.render();
        mapManager.checkDoors(dungeonMapManager);
        sb.begin();
        HUD.draw(sb);
        for(Item x : mapManager.getItemList()){
            x.sprite.draw(sb);
        }
        player.sprite.draw(sb);
      //  boss.sprite.draw(sb);
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

    public void playerConstructor(String p, float s, int h, int d){
        playerTexture = new Texture(Gdx.files.internal(p));
        player = new Player(playerTexture,s,d,h,WIDTH/2,HEIGHT/2);
    }
}
