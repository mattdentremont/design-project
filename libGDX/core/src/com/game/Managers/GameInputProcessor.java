package com.game.Managers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.game.Entities.Player;
import com.game.States.GameState;

public class GameInputProcessor extends InputAdapter {
    public Player player;
    public GameStateManager gsm;
    public static int WIDTH;
    public static int HEIGHT;
    public static final float speed = 200f;
    public boolean isFlipped;

   public GameInputProcessor(Player player, GameStateManager gsm)
    {
        this.player = player;
        this.gsm = gsm;
    }

    public void movePlayer(float dt)
    {
        if(Gdx.input.isKeyPressed(Input.Keys.ESCAPE)) {//TODO: PAUSE MENU IMPLEMENTATION
            gsm.setState(GameStateManager.MENU);
        }
        if(Gdx.input.isKeyPressed(Input.Keys.A))//MOVE LEFT
        {
            player.translatePlayer(-speed*dt,0);
        }
        if(Gdx.input.isKeyPressed(Input.Keys.D))//MOVE RIGHT
        {

            player.translatePlayer(speed*dt,0);
        }
        if(Gdx.input.isKeyPressed(Input.Keys.W))//MOVE UP
        {
            player.translatePlayer(0,speed*dt);
        }
        if(Gdx.input.isKeyPressed(Input.Keys.S))//MOVE DOWN
        {
            player.translatePlayer(0,-speed*dt);
        }
/*
        if(playerSprite.getX() <= 0)
        {
            playerSprite.setPosition(0,playerSprite.getY());
        }
        else if(playerSprite.getX() >= 1920-playerSprite.getWidth())
        {
            playerSprite.setPosition(1920-playerSprite.getWidth(),playerSprite.getY());
        }
        if(playerSprite.getY() <= 0)
        {
            playerSprite.setPosition(playerSprite.getX(),0);
        }
        else if(playerSprite.getY() >= 1080-playerSprite.getHeight())
        {
            playerSprite.setPosition(playerSprite.getX(),1080-playerSprite.getHeight());
        }*/

        //TODO: COLLISION MANAGER

    }

}
