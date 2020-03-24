package com.game.States;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.game.Entities.Enemy;
import com.game.Entities.Player;
import com.game.Managers.*;

public class PauseState extends GameState {

    private GameState currentGameState;
    private BitmapFont font;
    private GlyphLayout layout;
    private SpriteBatch sb;
    private Preferences prefs;

    public TiledMapManager mapManager;
    public UI HUD;
    private Player player;
    private Enemy[] enemies;

    public PauseState(GameStateManager gsm,GameState currentGameState,Player player, Enemy[] enemies, UI HUD, TiledMapManager mapManager)
    {
        super(gsm);
        this.currentGameState = currentGameState;
        layout = new GlyphLayout();
        this.player = player;
        this.enemies = enemies;
        this.HUD = HUD;
        this.mapManager = mapManager;
    }
    @Override
    public void init() {
        sb = new SpriteBatch();
        layout = new GlyphLayout();
        font = new BitmapFont();
        font.getData().setScale(2f);
        font.setColor(Color.WHITE);

    }

    @Override
    public void update(float dt) {
        handleInput(dt);
    }

    @Override
    public void draw() {
        sb.setProjectionMatrix(game.cam.combined);
        mapManager.render();
        sb.begin();
        HUD.draw(sb);
        player.sprite.draw(sb);
       /* for(Enemy x : enemies) {
            if(x != null)
                x.sprite.draw(sb);
        }*/ //enemies need to be in array. Once that array is no longer null then this should work.
        font.draw(sb,"PAUSED. PRESS ENTER TO RESUME GAME!",0,game.HEIGHT/2 );
        sb.end();

    }

    @Override
    public void handleInput(float dt) {
        if(Gdx.input.isKeyPressed(Input.Keys.ENTER)) {
            gsm.unpauseGame(currentGameState);
        }
    }

    @Override
    public void dispose() {
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
