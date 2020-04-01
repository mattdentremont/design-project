package com.game.Managers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.game.Entities.Beer;
import com.game.Entities.Enemy;
import com.game.Entities.Item;
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
    public  float speed;
    private ArrayList<Enemy> enemyList;
    public Texture attackTexture;

    public boolean usedRedBull;
    private float RedBullTimer;
    public boolean usedBeer;
    private float BeerTimer;

   public GameInputProcessor(Player player, GameStateManager gsm, escapeGame game, ArrayList<Enemy> enemyList)
    {
        this.player = player;
        this.speed = player.speed;
        this.gsm = gsm;
        this.game = game;
        WIDTH = game.WIDTH;
        HEIGHT = game.HEIGHT;
        this.enemyList = enemyList;
        this.usedBeer = false;
        this.usedRedBull = false;
    }

    public void movePlayer(float dt)
    {
        Item beer = player.getInventory()[0];
        Item redbull = player.getInventory()[1];

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
        if(Gdx.input.isKeyJustPressed(Input.Keys.RIGHT))//ATTACK RIGHT
        {
//            attackTexture = new Texture(Gdx.files.internal("PRight.png"));
//            player.sprite.setTexture(attackTexture);
            for(Enemy x: enemyList) {
                if((x.getPosX()>=player.getPosX()) &&x.getPosX() <= player.getPosX() +75f)
                {
                    if((x.getPosY() > player.getPosY() &&(x.getPosY() - player.getPosY()) <= 50f) ||(x.getPosY() <= player.getPosY() &&(x.getPosY() - player.getPosY()) <= 50f))
                        x.takeDamage(player.damage);
                }
            }
        }
        if(Gdx.input.isKeyJustPressed(Input.Keys.LEFT))//ATTACK LEFT
        {
            for(Enemy x: enemyList) {
                if((x.getPosX()<=player.getPosX()) && x.getPosX() >= player.getPosX() -75f)
                {
                    if((x.getPosY() > player.getPosY() &&(x.getPosY() - player.getPosY()) <= 50f) ||(x.getPosY() <= player.getPosY() &&(x.getPosY() - player.getPosY()) <= 50f))
                    x.takeDamage(player.damage);
                }
            }
        }
        if(Gdx.input.isKeyJustPressed(Input.Keys.UP))//ATTACK UP
        {
            for(Enemy x: enemyList) {
                if((x.getPosY()>=player.getPosY()) &&x.getPosY() <= player.getPosY() +75f)
                {
                    if((x.getPosX() > player.getPosX() &&(x.getPosX() - player.getPosX()) <= 50f) ||(x.getPosY() <= player.getPosY() &&(x.getPosX() - player.getPosX()) <= 50f))
                        x.takeDamage(player.damage);
                }
            }
        }
        if(Gdx.input.isKeyJustPressed(Input.Keys.DOWN))//ATTACK DOWN
        {
            for(Enemy x: enemyList) {
                if((x.getPosY()<=player.getPosY()) && x.getPosY() >= player.getPosY() -75f)
                {
                    if((x.getPosX() > player.getPosX() &&(x.getPosX() - player.getPosX()) <= 50f) ||(x.getPosY() <= player.getPosY() &&(x.getPosX() - player.getPosX()) <= 50f))
                        x.takeDamage(player.damage);
                }
            }
        }
        if(Gdx.input.isKeyJustPressed(Input.Keys.F))//ULT
        {
            for(Enemy x: enemyList) {
                x.takeDamage(100);
            }
        }

        if(Gdx.input.isKeyJustPressed(Input.Keys.Q))//DRINK BEER - starts timer
        {
            if(beer != null)//if you have a beer
            {
                if (BeerTimer ==0) {//and timer hasn't started
                    BeerTimer += dt;//start the timer
                    beer.use();//use beer effects
                }
            }
        }
        if(BeerTimer > 0)
        {
            if (BeerTimer <=10) {
                BeerTimer += dt;
                beer.use();
            }
            else{
                BeerTimer = 0;
                beer.end();
                player.getInventory()[0] = null;
            }

        }

        if(Gdx.input.isKeyJustPressed(Input.Keys.E))//DRINK REDBULL - starts timer
        {
            if(redbull != null)//if you have a redbull
            {
                if (RedBullTimer <= 10) {
                    RedBullTimer += dt;
                    redbull.use();
                }
            }
        }
        if(RedBullTimer > 0)
        {
            if (RedBullTimer <=10) {
                RedBullTimer += dt;
                redbull.use();
            }
            else{
                RedBullTimer = 0;
                redbull.end();
                player.getInventory()[1] = null;
            }

        }
    }

}
