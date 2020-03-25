package com.game.States;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.game.Entities.Enemy;
import com.game.Entities.Player;
import com.game.Managers.GameStateManager;
import com.game.Managers.TiledMapManager;
import com.game.Managers.UI;

import java.util.ArrayList;


public class HighScoreState extends GameState {

    private BitmapFont font;
    private BitmapFont font2;
    private GlyphLayout layout;
    private SpriteBatch sb;
    private Preferences prefs;

    public HighScoreState(GameStateManager gsm) {
        super(gsm);
        layout = new GlyphLayout();
    }

    @Override
    public void init() {
        sb = new SpriteBatch();
        layout = new GlyphLayout();
        font = new BitmapFont();
        font2 = new BitmapFont();
        font.getData().setScale(2f);
        font.setColor(Color.WHITE);
        font2.getData().setScale(2f);
        font2.setColor(Color.GREEN);
        prefs = Gdx.app.getPreferences("GameStorage");
    }

    @Override
    public void update(float dt) {
        handleInput(dt);
    }

    @Override
    public void draw() {

        sb.setProjectionMatrix(game.cam.combined);
        sb.begin();
        String Score = Integer.toString(prefs.getInteger("highScore"));;
        String roomsVisited = Integer.toString(prefs.getInteger("Rooms Visited"));
        String enemiesDefeated = Integer.toString(prefs.getInteger("Enemies Defeated"));

        String scoreLine = "High Score: " + Score;
        String roomsLine = "Rooms Visited on this run: " + roomsVisited;
        String enemiesLine = "Enemies Defeated on this run: " + enemiesDefeated;

        layout.setText(font,scoreLine);
        font.draw(sb,scoreLine,0,game.HEIGHT/2 + 50);
        font.draw(sb,roomsLine,0,game.HEIGHT/2 );
        font.draw(sb,enemiesLine,0,game.HEIGHT/2 - 50);
        font2.draw(sb,"TO RETURN TO MENU PRESS THE ESCAPE KEY",0,game.HEIGHT/2 - 100);
        sb.end();
    }

    @Override
    public void handleInput(float dt) {
        if(Gdx.input.isKeyPressed(Input.Keys.ESCAPE)) {
            gsm.setState(GameStateManager.MENU);
        }
    }

    @Override
    public void dispose() {
        font.dispose();
        font2.dispose();
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
