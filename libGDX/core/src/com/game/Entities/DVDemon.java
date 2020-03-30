package com.game.Entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.game.Behaviors.Contact;
import com.game.Behaviors.WallBounce;

public class DVDemon extends Enemy
{
    public DVDemon(float posX, float posY)
    {
        super(posX, posY);
        this.sprite = new Sprite(new Texture(Gdx.files.internal("DVDemon.png")));
        this.damageValue = 20;
        this.maxHealth = 200;
        this.movementSpeed = 200f;
        this.movementPattern = new WallBounce();
        this.attackPattern = new Contact();
        this.currentHealth = this.maxHealth;
        this.posX = posX;
        this.posY = posY;
        this.isDead = false;
        this.sprite.setPosition(posX, posY);
        this.attackDelayCnt = 0;
        this.attackDelayTime = 0.5f;
        this.heading = 4;
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
    public boolean takeDamage(int damageTaken)
    {
        this.currentHealth -= damageTaken;
        if (this.currentHealth <= 0)
        {
            isDead = true;
            return true;
        }
        else {
            return false;
        }
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

    @Override
    public boolean checkDead()
    {
        return isDead;
    }

    @Override
    public void setPosition(float x, float y)
    {
        posX = x;
        posY = y;
        sprite.setPosition(posX,posY);
    }
}
