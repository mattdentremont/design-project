package com.game.Managers;

import com.game.States.GameState;
import com.game.States.MenuState;
import com.game.States.PlayState;

public class GameStateManager {

    private GameState currentState;


    public static final int MENU = 0;
    public static final int PLAY = 1;
    //public static final int HIGHSCORES = 2;

    public GameStateManager() {
        setState(MENU);//Start in MENU state.
    }

    public void setState(int state) {
        if (currentState != null) {
            currentState.dispose();
        }
        if (state == MENU) {
            //switch to menu
            currentState = new MenuState(this);
        }
        if (state == PLAY) {
            //switch to play state
            currentState = new PlayState(this);
        }

      /*  if(state==HIGHSCORES){
            //switch to play state
            currentState = new PlayState(this);*/
    }


    public void update(float dt){
        currentState.update(dt);
    }
    public void draw(){
        currentState.draw();
    }


}