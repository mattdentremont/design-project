package com.game.Entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.game.Behaviors.Attack;

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

    public Player(Texture texture, float speed, int damage, int health,int posX,int posY)
    {
        super(texture, speed, damage, health,posX,posY);
        this.sprite = new Sprite(texture);
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
        }
        else{
            inventory[1] = item;
        }
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


//    public void ult(float dt){
//
//    }
}
