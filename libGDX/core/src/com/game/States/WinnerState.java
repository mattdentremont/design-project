package com.game.States;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.game.Entities.Enemies.Enemy;
import com.game.Entities.Player;
import com.game.Managers.*;
import java.util.ArrayList;

//State entered when player beat the game:
public class WinnerState extends GameState {

    private GameState currentGameState;
    private BitmapFont font;
    private SpriteBatch sb;

    public TiledMapManager mapManager;
    public UI HUD;
    private Player player;
    private ArrayList<Enemy> enemies;

    public WinnerState(GameStateManager gsm, GameState currentGameState, Player player, ArrayList<Enemy> enemies, UI HUD, TiledMapManager mapManager)
    {
        super(gsm);
        init();
        this.currentGameState = currentGameState;
        this.player = player;
        this.enemies = enemies;
        this.HUD = HUD;
        this.mapManager = mapManager;
    }
    @Override
    public void init() {
        sb = new SpriteBatch();
        font = new BitmapFont();
        font.getData().setScale(2f);
        font.setColor(Color.GREEN);

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
        for(Enemy x : enemies) {
            if(x != null)
                x.sprite.draw(sb);
        } //enemies need to be in array. Once that array is no longer null then this should work.
        font.draw(sb,"YOU CLEARED ALL ROOMS!",200,game.HEIGHT/2 );
        font.draw(sb,"PRESS ENTER TO START A NEW GAME",50,game.HEIGHT/2 -50);
        font.draw(sb,"PRESS ESC TO RETURN TO THE MAIN MENU",50,game.HEIGHT/2 -100);
        sb.end();

    }

    @Override
    public void handleInput(float dt) {
        if(Gdx.input.isKeyPressed(Input.Keys.ENTER)) {
            gsm.setState(gsm.CHARACTERS);
        }
        if(Gdx.input.isKeyPressed(Input.Keys.ESCAPE)) {
            gsm.setState(gsm.MENU);
        }
    }

    @Override
    public void dispose() {
        font.dispose();
        currentGameState.dispose();
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
