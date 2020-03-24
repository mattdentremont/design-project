package com.game.Entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.game.Behaviors.AI;

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

    Enemy(float posX, float posY)
    {
        this.posY = posY;
        this.posX = posX;
        this.isDead = false;
    }
    public abstract void attack(Player player);

    public abstract void takeDamage(int damageTaken);

    public abstract void move(Player player, float movementSpeed, float dt);
}
