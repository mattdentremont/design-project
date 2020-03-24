package com.game.States;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.game.Entities.Enemy;
import com.game.Entities.Player;
import com.game.Managers.GameStateManager;
import com.badlogic.gdx.Input;
import com.game.Managers.TiledMapManager;
import com.game.Managers.UI;
import com.game.main.escapeGame;

public class MenuState extends GameState {

    private SpriteBatch sb;
    private escapeGame game;
    private BitmapFont titleFont;
    private BitmapFont font;
    private final String title = "Dungeon Escape";
    private int currentItem;
    private String[] menuItems;
    private GlyphLayout layout;

    public MenuState(GameStateManager gsm)
    {
        super(gsm);
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
        titleFont.draw(sb,title,(escapeGame.WIDTH-fontWidth)/2,900);
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
            font.draw(sb,menuItems[i],(escapeGame.WIDTH-fontWidth)/2, 200-35*i);
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
                gsm.setState(GameStateManager.PLAY);
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
    }

    @Override
    public TiledMapManager getmapManager() {
        return null;
    }

    @Override
    public Enemy[] getEnemies() {
        return new Enemy[0];
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
