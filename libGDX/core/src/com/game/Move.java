package com.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class Move {
    Sprite playerSprite;
    public static int WIDTH;
    public static int HEIGHT;

   Move(Sprite x, int width, int height)
    {
        playerSprite = x;
        WIDTH = width;
        HEIGHT = height;
    }

    public void movePlayer()
    {
        if(Gdx.input.isKeyPressed(Input.Keys.A))
        {
            if(!playerSprite.isFlipX()) {
                playerSprite.flip(true, false);
            }
            playerSprite.translateX(-10.0f);
        }
        else if(Gdx.input.isKeyPressed(Input.Keys.D))
        {
            if(playerSprite.isFlipX()) {
                playerSprite.flip(true, false);
            }
            playerSprite.translateX(10.0f);
        }
        else if(Gdx.input.isKeyPressed(Input.Keys.W))
        {
            playerSprite.translateY(10.0f);
        }
        else if(Gdx.input.isKeyPressed(Input.Keys.S))
        {
            playerSprite.translateY(-10.0f);
        }

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
        }

    }

}
