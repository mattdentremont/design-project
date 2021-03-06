package com.game.Entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.game.Behaviors.Animation;
import com.game.Entities.Pickups.Item;

//Player class. This is the character the user controls.
public class Player extends Character {

    public Sprite sprite; //The Player Sprite (the thing you see)
    private boolean isDead; //Is the player dead?
    public float speed; //Current Speed
    public int damage; //Current Damage Output
    public int maxHealth; //Current Maximum Health Total
    public int health; //Current Health Total
    public float posX;
    public float posY;

    //User Interface Variables
    private int score;
    private int roomsVisited;
    private int enemiesDefeated;

    //Values without modification from Power-ups
    public int normalDamage;
    public float normalSpeed;
    public int normalHealth;

    //Item Inventory
    private Item[] inventory;

    private float ultTimer; //Countdown for ULT usage
    private boolean hasKey; //Sets doors to be unlocked after finishing a room

    //Animation Requirements
    public Animation playerAnimation;
    private Texture texture;

    //Sounds
    private Sound keyPickup = Gdx.audio.newSound(Gdx.files.internal("sounds/key.mp3"));
    private Sound beerPickup = Gdx.audio.newSound(Gdx.files.internal("sounds/beerPickup.mp3"));
    private Sound redbullPickup = Gdx.audio.newSound(Gdx.files.internal("sounds/redbullPickup.mp3"));
    private Sound gotHitSound = Gdx.audio.newSound(Gdx.files.internal("sounds/playerOof.mp3"));

    //Hitbox Boxes
    public Rectangle N;
    public Rectangle E;
    public Rectangle S;
    public Rectangle W;

    public Player(String texturePath, float speed, int damage, int health,int posX,int posY)
    {
        super(texturePath, speed, damage, health,posX,posY);
        this.texture = new Texture(texturePath);
        this.playerAnimation = new Animation(new TextureRegion(texture), 2, 0.5f);
        this.sprite = new Sprite(playerAnimation.getFrame());
        this.sprite.setScale(2);
        this.normalDamage = damage;
        this.damage= damage;
        this.normalSpeed = speed;
        this.speed = speed;
        this.health = health;
        this.normalHealth = health;
        this.maxHealth = health;
        this.posX = posX;
        this.posY = posY;
        this.isDead = false;
        this.sprite.setScale(1f);
        this.sprite.setPosition(posX,posY);
        this.score = 0;
        this.roomsVisited = 1;
        this.enemiesDefeated = 0;
        this.inventory = new Item[2];
        this.hasKey = false;
        this.N = new Rectangle(0,0,this.sprite.getWidth(), 1.5f * this.sprite.getHeight());
        this.E = new Rectangle(0,0,1.5f * this.sprite.getWidth(), this.sprite.getHeight());
        this.S = new Rectangle(0,0,this.sprite.getWidth(), 1.5f * this.sprite.getHeight());
        this.W = new Rectangle(0,0,1.5f * this.sprite.getWidth(), this.sprite.getHeight());
    }

    //Move the four attack hitboxes (called every PlayState update)
    public void updateHitboxes()
    {
        this.N.setPosition(this.sprite.getX(),this.sprite.getY() + this.sprite.getHeight());
        this.E.setPosition(this.sprite.getX() + this.sprite.getWidth(), this.sprite.getY());
        this.S.setPosition(this.sprite.getX(),this.sprite.getY() - (1.5f*this.sprite.getHeight()));
        this.W.setPosition(this.sprite.getX() - (1.5f*this.sprite.getWidth()),this.sprite.getY());
    }

    //Translate/Move the player and manage flipping sprites
    public void translatePlayer(float x,float y)
    {
        posX = posX + x;
        posY = posY + y;
        sprite.setPosition(posX,posY);
    }

    //Pick up new item and return the dropped item.
    //Can return null.
    public void pickUp(Item item)
    {
        item.setPickedUp(true);
        if(item.type == "BEER")
        {
            inventory[0] = item;
            beerPickup.play(1f);
        }
        else if(item.type == "REDBULL"){
            inventory[1] = item;
            redbullPickup.play(1f);
        }
        else if(item.type == "KEY"){
            this.hasKey = true;//picked up this rooms key.
            keyPickup.play(1f);
        }
    }

    public boolean checkKey()
    {
        return this.hasKey;
    }

    public void setKey(boolean status)
    {
        this.hasKey = status;
    }

    public Item[] getInventory()
    {
        return inventory;
    }

    //Increases at a steady state and when enemies die
    public void incrementUltCharge(float inc)
    {
        this.ultTimer+=inc;
        if(ultTimer > 60)
            ultTimer = 60;
    }

    public void resetUlt()
    {
        this.ultTimer = 0;
    }

    public float getUltCharge()
    {
        return this.ultTimer/60*100;
    }

    //Can the player ult or not
    public boolean canUlt()
    {
        if(ultTimer >=60)
            return true;
        else return false;
    }

    public void setPosition(float x, float y)
    {
        posX =  x;
        posY =  y;
        sprite.setPosition(posX,posY);
    }

    public float getPosX()
    {
        return posX;
    }

    public float getPosY()
    {
        return posY;
    }

    public int getScore()
    {
        return score;
    }

    public int getHealth()
    {
        return health;
    }

    public int getRoomsVisited()
    {
        return roomsVisited;
    }

    public int getEnemiesDefeated()
    {
        return enemiesDefeated;
    }

    public void incrementScore(int scoreAdded)
    {
        this.score+=scoreAdded;
    }

    public void incrementRoomsVisited()
    {
        this.roomsVisited++;
    }

    public void incrementEnemiesDefeated()
    {
        this.enemiesDefeated++;
    }

    public void gotHit(float damageTaken)
    {
        this.gotHitSound.play(1f);
        this.health -= damageTaken;
        if (this.health <= 0)
        {
            this.isDead = true;
        }
    }

    public boolean checkDead()
    {
        return this.isDead;
    }

    public void useRedBull()
    {
        this.speed = 2*normalSpeed;
        this.damage = 2*normalDamage;
    }

    public void endUseRedBull()
    {
        this.speed = normalSpeed;
        this.damage = normalDamage;
    }

    public void useBeer()
    {
        this.maxHealth = 2*normalHealth;
        this.health = 2*normalHealth;
    }

    public void endUseBeer()
    {
        this.maxHealth = normalHealth;
        if(health > normalHealth)
            this.health = normalHealth;
    }

}
