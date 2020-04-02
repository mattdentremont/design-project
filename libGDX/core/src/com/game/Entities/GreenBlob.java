package com.game.Entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.game.Behaviors.AI;
import com.game.Behaviors.Contact;
import com.game.Behaviors.targetPlayer;

import java.util.Random;

public class GreenBlob extends Enemy {

    public GreenBlob(float posX, float posY,int balancer)
    {
        super(posX, posY,balancer);//because damage and health scale with progression
        String[] enemyTextures = {"BobbyBlob.png","JohnWick.png","jamil.png"};
        int rand = new Random().nextInt(enemyTextures.length);
        this.sprite = new Sprite(new Texture(Gdx.files.internal(enemyTextures[rand])));
        this.damageValue = 10 + balancer;
        this.maxHealth = 20 + balancer;
        this.movementSpeed = 100f + balancer;
        this.movementPattern = new targetPlayer();
        this.attackPattern = new Contact();
        this.currentHealth = this.maxHealth;
        this.posX = posX;
        this.posY = posY;
        this.isDead = false;
        this.sprite.setPosition(posX, posY);
        this.attackDelayCnt = 0;
        this.attackDelayTime = 1f;
        this.isBoss = false;
    }

    @Override
    public void attack(Player player, float dt) {
        this.attackDelayCnt += dt;
        if (this.attackDelayCnt >= this.attackDelayTime) {
            this.attackPattern.attack(player, this);
            this.attackDelayCnt = 0;
        }
    }


    @Override
    public boolean takeDamage(int damageTaken) {
        this.currentHealth -= damageTaken;
        if (this.currentHealth <= 0){
            isDead = true;
            return true;
        }
        else
        {
            return false;
        }
    }

    @Override
    public boolean checkDead()
    {
        return isDead;
    }


    @Override
    public void move(Player player, float movementSpeed, float dt) {
        this.movementPattern.move(this, player, this.movementSpeed, dt);
    }

    @Override
    public float getPosX() {
        return this.posX;
    }

    @Override
    public float getPosY() {
        return this.posY;
    }
    public void setPosition(float x,float y) {
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
        posX = x;
        posY = y;
        sprite.setPosition(posX,posY);
    }

}
