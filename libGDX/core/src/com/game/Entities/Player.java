package com.game.Entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.game.Animation.*;

public class Player extends Character {
    public Sprite sprite;
    public boolean flipSprite;//false when facing right. true when facing left.
    boolean isDead;
    public float speed;
    public int damage;
    public int maxHealth;
    public int health;
    public float posX;
    public float posY;
    public int score;
    public int roomsVisited;
    public int enemiesDefeated;

    public int normalDamage;
    public float normalSpeed;
    public int normalHealth;

    private Item[] inventory;
    private float ultTimer;
    private boolean hasKey;

    public Animation playerAnimation;
    private Texture texture;
    private Sound keyPickup = Gdx.audio.newSound(Gdx.files.internal("sounds/key.mp3"));
    private Sound beerPickup = Gdx.audio.newSound(Gdx.files.internal("sounds/beerPickup.mp3"));
    private Sound redbullPickup = Gdx.audio.newSound(Gdx.files.internal("sounds/redbullPickup.mp3"));
    private Sound gotHitSound = Gdx.audio.newSound(Gdx.files.internal("sounds/playerOof.mp3"));


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
    }


    public void translatePlayer(float x,float y) {
       if(x<0 && flipSprite==false)
       {
           flipSprite = true;
           sprite.flip(true,false);
       }
       else if(x>0 && flipSprite == true)
       {
           flipSprite = false;
           sprite.flip(true,false);
       }
        posX = posX + x;
        posY = posY + y;
        sprite.setPosition(posX,posY);
    }

    //pick up new item and return the dropped item.
    //can return null.
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

    public void setKey(boolean status){
        this.hasKey = status;
    }


    public Item[] getInventory(){
        return inventory;
    }

    public void regenHealth(int healthAdded)//regenerate player health.
    {
        if(healthAdded + health <= maxHealth)
            health+=healthAdded;
        else
            health = maxHealth;
    }

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
