package com.game.Managers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.game.Behaviors.Animation;
import com.game.Entities.Enemies.Enemy;
import com.game.Entities.Pickups.Item;
import com.game.Entities.Player;
import com.game.main.escapeGame;

import java.util.ArrayList;

//Processes inputs and keeps track of when inputs can be used.
//This class also plays sounds and checks hitboxes/bounds of the game when inputs are given.
public class GameInputProcessor extends InputAdapter {
    public Player player;
    public escapeGame game;
    public GameStateManager gsm;
    public static int WIDTH;
    public static int HEIGHT;
    private ArrayList<Enemy> enemyList;

    //Timers and flags for items:
    public boolean usedRedBull;
    private float RedBullTimer;
    public boolean usedBeer;
    private float BeerTimer;

    //Variables that define effect lengths for items:
    private float BeerEffectLength;
    private float RedBullEffectLength;

    //Attack timers/flags and animations
    public Animation attackAnimation;
    private Texture attackTexture;
    private float attackDuration;
    public  float speed;
    private float attackCnt;
    private boolean attacked;
    private boolean attacking;

    //sound effects:
    private Sound beerDrink = Gdx.audio.newSound(Gdx.files.internal("sounds/beerDrink.mp3"));
    private Sound redbullDrink = Gdx.audio.newSound(Gdx.files.internal("sounds/redbullDrink.mp3"));
    private Sound ultSound = Gdx.audio.newSound(Gdx.files.internal("sounds/ult.mp3"));
    private String characterPath;

    //constructor:
   public GameInputProcessor(Player player, GameStateManager gsm, escapeGame game, ArrayList<Enemy> enemyList,String sheetPath)
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
        this.BeerEffectLength = 10f;
        this.RedBullEffectLength = 10f;
        this.attackDuration = 0.2f;
        this.attackCnt = 0;
        this.attacked = false;
        this.attacking = false;
        this.characterPath = sheetPath;
    }

    public void movePlayer(float dt)
    {
        /*
        dt is the time elapsed since the last call!
        */
        //increment ult charge.
        player.incrementUltCharge(dt);
        Item beer = player.getInventory()[0];
        Item redbull = player.getInventory()[1];
        this.speed = player.speed;

        //Pause the game:
        if(Gdx.input.isKeyPressed(Input.Keys.ESCAPE)) {
            gsm.pauseGame(gsm.getCurrentState());
        }

        //MOVING. Note movement is bound by the size of the game so the player cannot move off of the screen:
        if((Gdx.input.isKeyPressed(Input.Keys.A))&&(player.getPosX() >= 0) || Gdx.input.isKeyPressed(Input.Keys.D)&& (player.getPosX() <= WIDTH - player.sprite.getWidth())) {
            if (Gdx.input.isKeyPressed(Input.Keys.A))//MOVE LEFT
            {
                if (Gdx.input.isKeyPressed(Input.Keys.W) && player.getPosY() <= HEIGHT-player.sprite.getHeight())
                    player.translatePlayer((float) (.7071) * (-1) * speed * dt, (float) (.7071) * speed * dt);
                else if (Gdx.input.isKeyPressed(Input.Keys.S) && player.getPosY() >= 0)
                    player.translatePlayer((float) (.7071) * (-1) * speed * dt, (float) (.7071) * (-1) * speed * dt);
                else
                    player.translatePlayer(-speed * dt, 0);
            }
            if (Gdx.input.isKeyPressed(Input.Keys.D))//MOVE RIGHT
            {
                if (Gdx.input.isKeyPressed(Input.Keys.W) && player.getPosY() <= HEIGHT-player.sprite.getHeight())
                    player.translatePlayer((float) (.7071) * speed * dt, (float) (.7071) * speed * dt);
                else if (Gdx.input.isKeyPressed(Input.Keys.S) && player.getPosY() >= 0)
                    player.translatePlayer((float) (.7071) * speed * dt, (float) (.7071) * (-1) * speed * dt);
                else
                    player.translatePlayer(speed * dt, 0);
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


        //ATTACKING
        if(Gdx.input.isKeyJustPressed(Input.Keys.RIGHT))//ATTACK RIGHT
        {
            //set animation flags and variables:
            this.attacked = true;
            this.attackTexture = new Texture(characterPath+"AttackRight-Sheet.png");
            this.attackAnimation = new Animation(new TextureRegion(attackTexture), 2, 0.2f);

            for(Enemy x: enemyList)
            {
                //If player can hit the enemy in the direction desired
                Rectangle enemyRectangle = x.sprite.getBoundingRectangle();
                if(player.E.overlaps(enemyRectangle))
                {
                    x.takeDamage(player.damage);
                }
            }
        }
        else if(Gdx.input.isKeyJustPressed(Input.Keys.LEFT))//ATTACK LEFT
        {
            //set animation flags and variables:
            this.attacked = true;
            this.attackTexture = new Texture(characterPath+"AttackLeft-Sheet.png");
            this.attackAnimation = new Animation(new TextureRegion(attackTexture), 2, 0.2f);
            for(Enemy x: enemyList)
            {
                //If player can hit the enemy in the direction desired
                Rectangle enemyRectangle = x.sprite.getBoundingRectangle();
                if(player.W.overlaps(enemyRectangle))
                {
                    x.takeDamage(player.damage);
                }
            }
        }
        else if(Gdx.input.isKeyJustPressed(Input.Keys.UP))//ATTACK UP
        {
            //set animation flags and variables:
            this.attacked = true;
            this.attackTexture = new Texture(characterPath+"AttackUp-Sheet.png");
            this.attackAnimation = new Animation(new TextureRegion(attackTexture), 2, 0.2f);
            for(Enemy x: enemyList)
            {
                //If player can hit the enemy in the direction desired
                Rectangle enemyRectangle = x.sprite.getBoundingRectangle();
                if(player.N.overlaps(enemyRectangle))
                {
                    x.takeDamage(player.damage);
                }
            }
        }
        else if(Gdx.input.isKeyJustPressed(Input.Keys.DOWN))//ATTACK DOWN
        {
            //set animation flags and variables:
            this.attacked = true;
            this.attackTexture = new Texture(characterPath+"AttackDown-Sheet.png");
            this.attackAnimation = new Animation(new TextureRegion(attackTexture), 2, 0.2f);
            for(Enemy x: enemyList)
            {
                //If player can hit the enemy in the direction desired
                Rectangle enemyRectangle = x.sprite.getBoundingRectangle();
                if(player.S.overlaps(enemyRectangle))
                {
                    x.takeDamage(player.damage);
                }
            }
        }
        else if(attacking == false){
            player.playerAnimation.update(dt);
            player.sprite.setRegion(player.playerAnimation.getFrame());
            this.attacked = false;
        }

        attackManager(dt);//manage attack animations


        if(Gdx.input.isKeyJustPressed(Input.Keys.F) && player.canUlt())//ULT
        {
            ultSound.play(1f);
            for(Enemy x: enemyList) {
                x.takeDamage(100);//the ultimate does 100 damage to all enemies in the room.
            }
            player.resetUlt();//reset ult
        }

        if(Gdx.input.isKeyJustPressed(Input.Keys.Q))//DRINK BEER - starts timer
        {
            if(beer != null)//if you have a beer start timers, use beer and play effects:
            {
                if (BeerTimer ==0) {
                    usedBeer = true;
                    BeerTimer += dt;//start the timer
                    player.useBeer();//use beer effects
                    player.getInventory()[0] = null;
                    beerDrink.play(1f);
                }
            }
        }
        if(usedBeer)//if a beer has been consumed update the timer before removing the effects afterwards:
        {
            BeerTimer+= dt;
            if (BeerTimer >=BeerEffectLength) {
                BeerTimer = 0;
                player.endUseBeer();
                usedBeer = false;
            }

        }

        if(Gdx.input.isKeyJustPressed(Input.Keys.E))//DRINK REDBULL
        {
            if(redbull != null)//if you have a redbull start timers, use redbull and play effects:
            {
                if (RedBullTimer == 0) {
                    usedRedBull = true;
                    RedBullTimer += dt;
                    player.useRedBull();
                    player.getInventory()[1] = null;
                    redbullDrink.play(1f);
                }
            }
        }
        if(usedRedBull)//if a redbull has been consumed update the timer before removing the effects afterwards:
        {
            RedBullTimer += dt;
            if (RedBullTimer >=RedBullEffectLength) {
                RedBullTimer = 0;
                usedRedBull = false;
                player.endUseRedBull();
            }

        }
    }

    //function to check attack timers and flags and manage animations:
    public void attackManager(float dt){
       if(this.attacked == true) {
           this.attackCnt += dt;
           if (this.attackCnt < this.attackDuration) {
               attackAnimation.update(dt);
               player.sprite.setRegion(attackAnimation.getFrame());
               this.attacking = true;
           }
           else if (this.attackCnt >= this.attackDuration) {
               this.attackCnt = 0;
               this.attacking = false;
           }
       }
    }


    //accessors:
    public boolean checkUsedRedBull()
    {
        return usedRedBull;
    }
    public boolean checkUsedBeer()
    {
        return usedBeer;
    }
    public float getBeerTimer()
    {
        return BeerTimer;
    }
    public float getRedBullTimer()
    {
        return RedBullTimer;
    }
    public float getBeerEffectLength()
    {
        return this.BeerEffectLength;
    }
    public float getRedBullEffectLength()
    {
        return this.RedBullEffectLength;
    }


}
