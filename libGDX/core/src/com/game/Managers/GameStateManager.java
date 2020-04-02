package com.game.Managers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.game.Entities.Enemy;
import com.game.Entities.Player;
import com.game.States.*;
import com.game.main.escapeGame;

public class GameStateManager {

    private GameState currentState;
    public escapeGame game;

    public static final int MENU = 0;
    public static final int PLAY = 1;
    public static final int CHARACTERS = 4;
    public static final int HIGHSCORES = 2;
    public static final int PAUSE = 3;
    public static final int DEAD = 3;
    public Texture playerTexture;
    public int playerDamage;
    public float playerSpeed;
    public int playerHealth;

    public GameStateManager(escapeGame g) {
        setState(MENU);//Start in MENU state.
        this.game = g;
    }

    public void setState(int state) {
        if (currentState != null) {
            currentState.dispose();
        }
        if (state == MENU) {
            //switch to menu
            currentState = new MenuState(this);
        }
//        if (state == PLAY) {
//            //switch to play state
//            currentState = new PlayState(this);
//        }

        if(state==HIGHSCORES) {
            //switch to highscore state
            currentState = new HighScoreState(this);
        }
        if(state==CHARACTERS) {
            //switch to character state
            currentState = new CharacterState(this);
        }
    }

    public void setPlayState(String t, float s, int h, int d){
        if (currentState != null) {
            currentState.dispose();
        }
        currentState = new PlayState(this,t,s,h,d);
    }

    public void player(){

    }

    public void pauseGame(GameState currentGameState)
    {
        currentState = new PauseState(this,currentGameState,currentGameState.getPlayer(), currentGameState.getEnemies(), currentGameState.getHUD(), currentGameState.getmapManager());
    }
    public void playerDied(GameState currentGameState)
    {
        currentState = new DeadState(this,currentGameState,currentGameState.getPlayer(), currentGameState.getEnemies(), currentGameState.getHUD(), currentGameState.getmapManager());
    }

    public void playerWon(GameState currentGameState)
    {
        currentState = new WinnerState(this,currentGameState,currentGameState.getPlayer(), currentGameState.getEnemies(), currentGameState.getHUD(), currentGameState.getmapManager());
    }


    public void unpauseGame(GameState desiredState)
    {
        currentState = desiredState;
    }


    public void update(float dt){
        currentState.update(dt);
    }
    public void draw(){
        currentState.draw();
    }

    public GameState getCurrentState()
    {
        return currentState;
    }

}
