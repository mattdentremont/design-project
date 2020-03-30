package com.game.States;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.game.Entities.Enemy;
import com.game.Entities.Player;
import com.game.Managers.GameStateManager;
import com.game.Managers.TiledMapManager;
import com.game.Managers.UI;
import com.game.main.escapeGame;

import java.util.ArrayList;

public class CharacterState extends GameState {

    private SpriteBatch sb;
    private escapeGame game;
    private BitmapFont font;
    private BitmapFont titleFont;
    private final String title = "Choose Your Fighter";
    private int currentItem;
    private String[] menuItems;
    private GlyphLayout layout;
    private Texture menuTexture;
    private Sprite menuSprite;
    private Music music;

    public CharacterState(GameStateManager gsm)
    {
        super(gsm);
        layout = new GlyphLayout();
    }

    @Override
    public void init() {

        sb = new SpriteBatch();
        titleFont = new BitmapFont();
        font = new BitmapFont();
        font.getData().setScale(3);
        font.setColor(Color.WHITE);
        menuItems = new String[] {"Dingus", "Jim"};
        menuTexture = new Texture(Gdx.files.internal("menuBackground.png"));
        menuSprite = new Sprite(menuTexture);
        music = Gdx.audio.newMusic(Gdx.files.internal("menuMusic.mp3"));
        music.setLooping(true);
        music.setVolume(.2f);
        music.play();
    }

    @Override
    public void update(float dt)
    {
        handleInput(dt);
    }

    @Override
    public void draw() {
        sb.setProjectionMatrix(game.cam.combined);
        sb.begin();
        layout.setText(titleFont,title);
        float fontWidth = layout.width;
        float fontHeight = layout.height;

        //draw Title
        menuSprite.draw(sb);
        titleFont.draw(sb,title,((escapeGame.WIDTH-fontWidth)/2), (escapeGame.HEIGHT - 100));

        //draw menu

    }

    @Override
    public void handleInput(float dt) {

    }

    @Override
    public void dispose() {

    }

    @Override
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
