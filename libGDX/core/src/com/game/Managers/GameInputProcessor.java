package com.game.Managers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.game.Entities.Player;
import com.game.States.GameState;
import com.game.main.escapeGame;

public class GameInputProcessor extends InputAdapter {
    public Player player;
    public escapeGame game;
    public GameStateManager gsm;
    public static int WIDTH;
    public static int HEIGHT;
    public static final float speed = 200f;

   public GameInputProcessor(Player player, GameStateManager gsm, escapeGame game)
    {
        this.player = player;
        this.gsm = gsm;
        this.game = game;
        WIDTH = game.WIDTH;
        HEIGHT = game.HEIGHT;
    }

    public void movePlayer(float dt)
    {
        if(Gdx.input.isKeyPressed(Input.Keys.ESCAPE)) {//TODO: PAUSE MENU IMPLEMENTATION
            gsm.setState(GameStateManager.MENU);
        }
        if(Gdx.input.isKeyPressed(Input.Keys.A))//MOVE LEFT
        {
            if(player.getPosX() >= 0)
            player.translatePlayer(-speed*dt,0);
        }
        if(Gdx.input.isKeyPressed(Input.Keys.D))//MOVE RIGHT
        {
            if(player.getPosX() <= WIDTH-player.sprite.getWidth())
            player.translatePlayer(speed*dt,0);
        }
        if(Gdx.input.isKeyPressed(Input.Keys.W))//MOVE UP
        {
            if(player.getPosY() <= HEIGHT-player.sprite.getHeight())
            player.translatePlayer(0,speed*dt);
        }
        if(Gdx.input.isKeyPressed(Input.Keys.S))//MOVE DOWN
        {
            if(player.getPosY() >= 0)
                player.translatePlayer(0,-speed*dt);
        }
        //TODO: COLLISION MANAGER - Maybe in another class.
        //TODO: MAP COLLISIONS - NOT ENEMY HIT DETECTION

    }

}
