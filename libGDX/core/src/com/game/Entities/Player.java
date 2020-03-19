package com.game.Entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class Player extends Character {

    public int damage;
    public int health;
    public float posX;
    public float posY;
    public Sprite sprite;
    public boolean flipSprite;//false when facing right. true when facing left.
    boolean isDead;
   public Player(Texture texture, int damage, int health,int posX,int posY)
    {
        super(texture, damage, health,posX,posY);
        this.sprite = new Sprite(texture);
        sprite.setScale(1f);
    }

    @Override
    public void hit(int dmg) {
        this.health -= dmg;
        if(health <= 0){
            isDead = true;
        }
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




}
