package com.game.Entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

public abstract class Enemy {
    public int damageValue;
    public int currentHealth;
    public float movementSpeed;
    Sprite sprite;
    boolean isDead;

    Enemy(Texture texture, int damageValue, int currentHealth)
    {
        sprite = new Sprite(texture);
        this.damageValue = damageValue;
        this.currentHealth = currentHealth;
        this.isDead = false;
    }
    public abstract void attack(Player player);

    public abstract void takeDamage(int damageTaken);

    public abstract void move(Sprite p); //Take in PlayerPos as param

}
