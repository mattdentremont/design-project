package com.game.Managers;

import com.game.States.GameState;
import com.game.States.MenuState;
import com.game.States.PlayState;

public class GameStateManager {

    private GameState currentState;

    public static final int MENU = 0;
    public static final int PLAY = 1;

    public GameStateManager(){
        setState(MENU);//TODO: CHANGE TO MENU STATE
    }
    public void setState(int state){
        if(currentState != null){
            currentState.dispose();
        }
        if(state==MENU){
            //switch to menu
            currentState = new MenuState(this);
        }
        if(state==PLAY){
                //switch to play state
            currentState = new PlayState(this);
        }

    }

    public void update(float dt){
        currentState.update(dt);
    }
    public void draw(){
        currentState.draw();
    }


}
