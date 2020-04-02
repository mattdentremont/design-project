package com.game.Entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.game.Behaviors.Cardinal;
import com.game.Behaviors.Contact;

import java.util.Random;

public class Wanderers extends Enemy {

    Wanderers(float startPosX, float startPosY,int balancer) {
        super(startPosX, startPosY,balancer);
        String[] enemyTextures = {"RedBlob.png", "integral.png"};
        int rand =  new Random().nextInt(enemyTextures.length);
        this.sprite = new Sprite(new Texture(Gdx.files.internal(enemyTextures[rand])));
        this.damageValue = 10+ balancer;;
        this.maxHealth = 20+ balancer;;
        this.movementSpeed = 100f+ balancer;;
        this.movementPattern = new Cardinal();
        this.attackPattern = new Contact();
        this.currentHealth = this.maxHealth;
        this.posX = startPosX;
        this.posY = startPosY;
        this.isDead = false;
        this.sprite.setPosition(startPosX, startPosY);
        this.attackDelayCnt = 0;
        this.attackDelayTime = 1f;
        this.moveDelayCnt = 0;
        this.moveDelayTime = 1f;
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
    public void move(Player player, float movementSpeed, float dt) {
        this.moveDelayCnt += dt;
        if(this.moveDelayCnt >= this.moveDelayTime) {
            this.randRoll = new Random().nextInt(5);
            this.moveDelayCnt = 0;
        }
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

    @Override
    public boolean checkDead() {
        return isDead;
    }

    @Override
    public void setPosition(float x, float y) {
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
