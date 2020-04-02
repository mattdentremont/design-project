package com.game.States;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import com.badlogic.gdx.math.Rectangle;
import com.game.Entities.*;
import com.game.Managers.*;
import com.game.main.escapeGame;

import java.util.ArrayList;
import java.util.Iterator;

public class PlayState extends GameState{


    private BitmapFont font;
    private GlyphLayout layout;
    private ProjectileEnemy enemy;


    private SpriteBatch sb;
    public static OrthographicCamera cam;
    public int WIDTH = 800;
    public int HEIGHT = 480;
    private Room currentRoom;
    private Texture playerTexture;
    private Player player;
    private Preferences prefs;
    private int dungeonWidth;
    private int dungeonHeight;


    public DungeonMapManager dungeonMapManager;
    public GameInputProcessor inputProcessor;
    public TiledMapManager mapManager;
    public UI HUD;
    public PlayState(GameStateManager gsm, String t, float s, int h, int d)
    {
        super(gsm);
        playerConstructor(t, s, h, d);
        init();
    }
    private Sprite menuSprite;
    private Music music;
    private Sound deathSound = Gdx.audio.newSound(Gdx.files.internal("sounds/bigOof.mp3"));

    @Override
    public void init() {
        sb = new SpriteBatch();
        font = new BitmapFont();
        font.getData().setScale(.5f);
        font.setColor(Color.RED);
        WIDTH = game.WIDTH;
        HEIGHT = game.HEIGHT;
        String[] maps = {"maps/generic.tmx","maps/satanic.tmx","maps/level1.tmx","maps/level2.tmx"};
        dungeonWidth = 10;
        dungeonHeight = 10;
        dungeonMapManager = new DungeonMapManager(maps,dungeonWidth,dungeonHeight,player);
        currentRoom = dungeonMapManager.getCurrentRoom();
        mapManager = new TiledMapManager(currentRoom.mapName,game,player);
        inputProcessor = new GameInputProcessor(player,this.gsm,game,mapManager.getEnemyList());
        cam = game.cam;
        HUD = new UI(player,cam,inputProcessor);
        prefs = Gdx.app.getPreferences("GameStorage");
        music = Gdx.audio.newMusic(Gdx.files.internal("sounds/Doom.mp3"));
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
            if(x.hasProjectiles)
            {
                x.getProjectiles(dt);
            }
            if(x.hasAnimation == true)
            {
                x.enemyAnimation.update(dt);
                x.sprite.setRegion(x.enemyAnimation.getFrame());
            }
        }
        for(Item x : items){
            if(playerHitBox.overlaps(x.sprite.getBoundingRectangle())){
                if (x.type == "BEER") {
                    if (playerInventory[0] == null) {
                        toPickup = x;
                    }
                }
                else if(x.type == "REDBULL"){
                    if (playerInventory[1] == null) {
                    toPickup = x;
                    }
                }
                else if(x.type == "KEY"){
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
        if(player.roomsVisited == dungeonHeight*dungeonWidth && enemies.size() == 0){
            gsm.playerWon(gsm.getCurrentState());
        }
        cam.update();
        mapManager.updateCam();

        //Handles Animations for attacks now too
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
      //boss.sprite.draw(sb);
        for (Enemy x :mapManager.getEnemyList()){
           x.sprite.draw(sb);
           if(x.currentHealth >= 0) {
               font.draw(sb, Integer.toString(x.currentHealth), (int) x.getPosX() + x.sprite.getWidth() / 2 - 5, (int) x.getPosY() + x.sprite.getHeight() + 5);
           }
           else{
               font.draw(sb, Integer.toString(0), (int) x.getPosX() + x.sprite.getWidth() / 2 - 5, (int) x.getPosY() + x.sprite.getHeight() + 5);
           }
            if(x.hasProjectiles)
            {
                ArrayList<Projectile> list = x.getProjectiles(0);
                for(Projectile p : list)
                {
                    p.sprite.draw(sb);
                }
            }
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
        //playerTexture = new Texture(Gdx.files.internal(p));
        player = new Player(p,s,d,h,WIDTH/2,HEIGHT/2);
    }
}
