package com.game.Entities;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.game.Behaviors.AI;
import com.game.Behaviors.Attack;

public abstract class Enemy {
    public int damageValue;
    public int maxHealth;
    public int currentHealth;
    public float movementSpeed;
    public float posX;
    public float posY;
    public Sprite sprite;
    boolean isDead;
    public AI movementPattern;
    public Attack attackPattern;
    public boolean flipSprite;
    public float attackDelayCnt;
    public float attackDelayTime;
    public int heading;
    public boolean isBoss;

    Enemy(float posX, float posY)
    {
        this.posY = posY;
        this.posX = posX;
        this.isDead = false;
        this.flipSprite = false;
        this.isBoss = false;
    }
    public abstract void attack(Player player, float dt);

    public abstract boolean takeDamage(int damageTaken);

    public abstract void move(Player player, float movementSpeed, float dt);

    public abstract float getPosX();

    public abstract float getPosY();
    public abstract boolean checkDead();
    public abstract void setPosition(float x,float y);
}
