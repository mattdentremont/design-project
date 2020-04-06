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
   // private ProjectileEnemy enemy; //Did not implement projectiles.


    private SpriteBatch sb;
    public static OrthographicCamera cam;
    public int WIDTH = 800;
    public int HEIGHT = 480;
    private Room currentRoom;
    private Player player;
    private Preferences prefs;
    private static final int dungeonWidth = 3;
    private static final int dungeonHeight = 1;
    private String spriteSheetPath;


    public DungeonMapManager dungeonMapManager;
    public GameInputProcessor inputProcessor;
    public TiledMapManager mapManager;
    public UI HUD;


    public PlayState(GameStateManager gsm, String playerTexture, float playerSpeed, int playerHealth, int playerDamage)
    {
        super(gsm);
        player = new Player(playerTexture,playerSpeed,playerDamage,playerHealth,WIDTH/2,HEIGHT/2);
        if(playerTexture.contains("Student"))//if this is a student character
        {
            this.spriteSheetPath ="student/";//sets path to student folder
        }
        else this.spriteSheetPath = "prof/";//otherwise sets to prof folder
        init();
    }
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
        dungeonMapManager = new DungeonMapManager(maps,dungeonWidth,dungeonHeight,player);
        currentRoom = dungeonMapManager.getCurrentRoom();
        mapManager = new TiledMapManager(currentRoom.mapName,game,player);
        inputProcessor = new GameInputProcessor(player,this.gsm,game,mapManager.getEnemyList(),spriteSheetPath);
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

        //
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

        //Check if the player has died:
        if(player.checkDead()){
            gsm.playerDied(gsm.getCurrentState());
            deathSound.play(1.0f);
        }

        //Check if the player has won the game by clearing all rooms:
        if(player.roomsVisited == dungeonHeight*dungeonWidth && enemies.size() == 0){
            gsm.playerWon(gsm.getCurrentState());
        }

        cam.update();//update the game cam.
        mapManager.updateCam();//update the game cam in the mapmanager.
        handleInput(dt);//handle keyboard input.
        player.updateHitboxes();//update player's hitboxes. (where they can hit enemies/be hit.)
        mapManager.checkDoors(dungeonMapManager);//checks if player is leaving the room
    }

    @Override
    public void draw() {
        sb.setProjectionMatrix(escapeGame.cam.combined);//sets projection matrix to correct

        //render the tiled map and draw it to the screen
        mapManager.render();
        //open the sprite batch to draw necessary elements to the screen:
        sb.begin();

        //draw the heads up display statistics to screen first:
        HUD.draw(sb);

        //Then draw items:
        for(Item x : mapManager.getItemList()){
            x.sprite.draw(sb);
        }
        //then draw the player:
        player.sprite.draw(sb);

        //Draw all enemies and draw health above them:
        for (Enemy x :mapManager.getEnemyList()){
           x.sprite.draw(sb);
           if(x.currentHealth >= 0) {
               font.draw(sb, Integer.toString(x.currentHealth), (int) x.getPosX() + x.sprite.getWidth() / 2 - 5, (int) x.getPosY() + x.sprite.getHeight() + 5);
           }
           else{
               font.draw(sb, Integer.toString(0), (int) x.getPosX() + x.sprite.getWidth() / 2 - 5, (int) x.getPosY() + x.sprite.getHeight() + 5);
           }
        }
        sb.end();
    }

    //Handle keyboard input.
    @Override
    public void handleInput(float dt) {

        inputProcessor.movePlayer(dt);
    }

    //save high scores and dispose of sounds and music and sprites.
    @Override
    public void dispose() {
        saveHighScore();
        sb.dispose();
        mapManager.dispose();
        HUD.dispose();
        music.dispose();
        deathSound.dispose();
    }


    //Saves High score to a file. Called when PlayState is disposed of.
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


    //Some Accessors
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
