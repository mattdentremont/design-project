package com.game.States;

import com.game.Managers.GameStateManager;
import com.game.main.escapeGame;

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

}
