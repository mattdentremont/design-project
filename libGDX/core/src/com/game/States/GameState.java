package com.game.States;

import com.game.Entities.Enemy;
import com.game.Entities.Player;
import com.game.Managers.GameStateManager;
import com.game.Managers.TiledMapManager;
import com.game.Managers.UI;
import com.game.main.escapeGame;

import java.util.ArrayList;

public abstract class GameState {

    protected GameStateManager gsm;
    public escapeGame game;

    protected GameState(GameStateManager gsm)
    {
        this.gsm = gsm;
        this.game = gsm.game;
        init();
    }

   public abstract void init();
    public abstract void update(float dt);
    public abstract void draw();
    public abstract void handleInput(float dt);
    public abstract void dispose();
    public abstract TiledMapManager getmapManager();
    public abstract ArrayList<Enemy> getEnemies();
    public abstract Player getPlayer();
    public abstract UI getHUD();

}
