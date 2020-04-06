package com.game.Managers;

import com.game.States.*;
import com.game.main.escapeGame;

public class GameStateManager {

    private GameState currentState;
    public escapeGame game;

    //States that do not need any parameters:
    public static final int MENU = 0;
    public static final int CHARACTERS = 1;
    public static final int HIGHSCORES = 2;


    public GameStateManager(escapeGame g) {
        setState(MENU);//Start in MENU state.
        this.game = g;
    }

    //Function to set game state:
    public void setState(int state) {
        if (currentState != null) {
            currentState.dispose();
        }
        if (state == MENU) {
            //switch to menu
            currentState = new MenuState(this);
        }
        if(state==HIGHSCORES) {
            //switch to highscore state
            currentState = new HighScoreState(this);
        }
        if(state==CHARACTERS) {
            //switch to character state
            currentState = new CharacterState(this);
        }
    }

    //Enter Play State:
    public void setPlayState(String t, float s, int h, int d){
        if (currentState != null) {
            currentState.dispose();
        }
        currentState = new PlayState(this,t,s,h,d);
    }


    //Enter pause state:
    public void pauseGame(GameState currentGameState)
    {
        currentState = new PauseState(this,currentGameState,currentGameState.getPlayer(), currentGameState.getEnemies(), currentGameState.getHUD(), currentGameState.getmapManager());
    }

    //Enter Dead state:
    public void playerDied(GameState currentGameState)
    {
        currentState = new DeadState(this,currentGameState,currentGameState.getPlayer(), currentGameState.getEnemies(), currentGameState.getHUD(), currentGameState.getmapManager());
    }

    //Enter winner state:
    public void playerWon(GameState currentGameState)
    {
        currentState = new WinnerState(this,currentGameState,currentGameState.getPlayer(), currentGameState.getEnemies(), currentGameState.getHUD(), currentGameState.getmapManager());
    }


    //Switch from current pause state to the desired state (play state):
    public void unpauseGame(GameState desiredState)
    {
        currentState = desiredState;
    }


    //Update state:
    public void update(float dt){
        currentState.update(dt);
    }

    //Draw the game state:
    public void draw(){
        currentState.draw();
    }

    //Accessor for current state:
    public GameState getCurrentState()
    {
        return currentState;
    }

}
