package com.game.States;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.game.Entities.Enemy;
import com.game.Entities.Player;
import com.game.Managers.GameStateManager;
import com.badlogic.gdx.Input;
import com.game.Managers.TiledMapManager;
import com.game.Managers.UI;
import com.game.main.escapeGame;

import java.util.ArrayList;

public class MenuState extends GameState {

    private SpriteBatch sb;
    private escapeGame game;
    private BitmapFont titleFont;
    private BitmapFont font;
    private final String title = "Dungeon Escape";
    private int currentItem;
    private String[] menuItems;
    private GlyphLayout layout;
    private Texture menuTexture;
    private Sprite menuSprite;
    private Music music;

    public MenuState(GameStateManager gsm)
    {
        super(gsm);
        init();
        layout = new GlyphLayout();
    }
    @Override
    public void init() {

        sb = new SpriteBatch();
        titleFont = new BitmapFont();
       font = new BitmapFont();
       titleFont.getData().setScale(5);
       font.getData().setScale(2);
        font.setColor(Color.WHITE);
        menuItems = new String[] {"Play Game", "Highscores", "Quit"};
        menuTexture = new Texture(Gdx.files.internal("menuBackground.jpg"));
        menuSprite = new Sprite(menuTexture);
        music = Gdx.audio.newMusic(Gdx.files.internal("menuMusic.mp3"));
        music.setLooping(true);
        music.setVolume(.2f);
        music.play();
    }

    @Override
    public void update(float dt) {
        handleInput(dt);
    }

    @Override
    public void draw() {
        sb.setProjectionMatrix(game.cam.combined);
        sb.begin();
        layout.setText(titleFont,title);
        float fontWidth = layout.width;
        float fontHeight = layout.height;
        //draw title
        menuSprite.draw(sb);
        titleFont.draw(sb,title,(escapeGame.WIDTH-fontWidth)/2,escapeGame.HEIGHT - 100);
        //draw menu
        for(int i = 0; i < menuItems.length; i++){
            layout.setText(titleFont,menuItems[i]);
            fontWidth = layout.width;
            if(currentItem == i) {
                font.setColor(Color.PURPLE);
            }
            else{
                font.setColor(Color.WHITE);
            }
            font.draw(sb,menuItems[i],escapeGame.WIDTH/2-75, 200-35*i);
        }
        sb.end();
    }

    @Override
    public void handleInput(float dt) {
        if (Gdx.input.isKeyJustPressed(Input.Keys.UP)) {
            if (currentItem > 0) {
                currentItem--;
            }
        }
            if (Gdx.input.isKeyJustPressed(Input.Keys.DOWN)) {
                if (currentItem < menuItems.length - 1) {
                    currentItem++;
                }
            }
            if (Gdx.input.isKeyJustPressed(Input.Keys.ENTER))
                select();
        }

        private void select(){
            //Switch to play state
            if(currentItem == 0) {
                gsm.setState(GameStateManager.CHARACTERS);
            }
            else if(currentItem == 1) {
               gsm.setState(GameStateManager.HIGHSCORES);
            }
            else if(currentItem == 2){
                Gdx.app.exit();
            }

        }

    @Override
    public void dispose(){
        sb.dispose();
        titleFont.dispose();
        font.dispose();
        music.dispose();
    }

    public TiledMapManager getmapManager() {
        return null;
    }

    @Override
    public ArrayList<Enemy> getEnemies() {
        return null;
    }

    @Override
    public Player getPlayer() {
        return null;
    }

    @Override
    public UI getHUD() {
        return null;
    }
}
