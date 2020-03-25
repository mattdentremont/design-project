package com.game.Entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.game.Behaviors.Attack;
import com.game.Behaviors.Cardinal;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

public class Player extends Character {
    public Sprite sprite;
    public boolean flipSprite;//false when facing right. true when facing left.
    boolean isDead;

    public int damage;
    public int health;
    public float posX;
    public float posY;
    public int score;
    public int roomsVisited;
    public int enemiesDefeated;
   public Player(Texture texture, int damage, int health,int posX,int posY)
    {
        super(texture, damage, health,posX,posY);
        this.sprite = new Sprite(texture);
        this.damage= damage;
        this.health = health;
        this.posX = posX;
        this.posY = posY;
        this.isDead = false;
        this.sprite.setScale(1f);
        this.sprite.setPosition(posX,posY);
        this.score = 0;
        this.roomsVisited = 1;
        this.enemiesDefeated = 0;
    }

    Attack attackType = new Cardinal();

//    @Override //TODO: Check for death and stuff
//    public void attack(Sprite player, String attackDirection) {
//        this.attackType.attack(player, attackDirection);
//        if(health <= 0){
//            isDead = true;
//        }
//    }

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
        //TODO: go to main menu state
    }
}
