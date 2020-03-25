package com.game.Managers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.game.Entities.Enemy;
import com.game.Entities.Player;
import com.game.States.GameState;
import com.game.main.escapeGame;

import java.util.ArrayList;

public class GameInputProcessor extends InputAdapter {
    public Player player;
    public escapeGame game;
    public GameStateManager gsm;
    public static int WIDTH;
    public static int HEIGHT;
    public static final float speed = 200f;
    private ArrayList<Enemy> enemyList;

   public GameInputProcessor(Player player, GameStateManager gsm, escapeGame game, ArrayList<Enemy> enemyList)
    {
        this.player = player;
        this.gsm = gsm;
        this.game = game;
        WIDTH = game.WIDTH;
        HEIGHT = game.HEIGHT;
        this.enemyList = enemyList;
    }

    public void movePlayer(float dt)
    {
        if(Gdx.input.isKeyPressed(Input.Keys.ESCAPE)) {
            gsm.pauseGame(gsm.getCurrentState());
        }
        if((Gdx.input.isKeyPressed(Input.Keys.A))&&(player.getPosX() >= 0) || Gdx.input.isKeyPressed(Input.Keys.D)&& (player.getPosX() <= WIDTH - player.sprite.getWidth())) {
            if (Gdx.input.isKeyPressed(Input.Keys.A))//MOVE LEFT
            {
                    if (Gdx.input.isKeyPressed(Input.Keys.W) && player.getPosY() <= HEIGHT-player.sprite.getHeight())
                        player.translatePlayer((float) (.7071) * (-1) * speed * dt, (float) (.7071) * speed * dt);
                    else if (Gdx.input.isKeyPressed(Input.Keys.S) && player.getPosY() >= 0)
                        player.translatePlayer((float) (.7071) * (-1) * speed * dt, (float) (.7071) * (-1) * speed * dt);
                    else player.translatePlayer(-speed * dt, 0);
            }
            if (Gdx.input.isKeyPressed(Input.Keys.D))//MOVE RIGHT
            {
                    if (Gdx.input.isKeyPressed(Input.Keys.W) && player.getPosY() <= HEIGHT-player.sprite.getHeight())
                        player.translatePlayer((float) (.7071) * speed * dt, (float) (.7071) * speed * dt);
                    else if (Gdx.input.isKeyPressed(Input.Keys.S) && player.getPosY() >= 0)
                        player.translatePlayer((float) (.7071) * speed * dt, (float) (.7071) * (-1) * speed * dt);
                    else player.translatePlayer(speed * dt, 0);
            }
        }
        else if(Gdx.input.isKeyPressed(Input.Keys.W))//MOVE UP
        {
            if(player.getPosY() <= HEIGHT-player.sprite.getHeight())
            player.translatePlayer(0,speed*dt);
        }
        else if(Gdx.input.isKeyPressed(Input.Keys.S))//MOVE DOWN
        {
            if(player.getPosY() >= 0)
                player.translatePlayer(0,-speed*dt);
        }
        if(Gdx.input.isKeyPressed(Input.Keys.RIGHT))//ATTACK RIGHT
        {
            for(Enemy x: enemyList) {
                if((x.getPosX() - player.getPosX()) >= 20f)
                {
                    x.takeDamage(player.damage);
                }
            }
        }
        if(Gdx.input.isKeyPressed(Input.Keys.LEFT))//ATTACK LEFT
        {
            for(Enemy x: enemyList) {
                if((player.getPosX() - x.getPosX()) >= 20f)
                {
                    x.takeDamage(player.damage);
                }
            }
        }
    }

}
