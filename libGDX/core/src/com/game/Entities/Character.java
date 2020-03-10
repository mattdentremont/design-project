package com.game.Entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

public abstract class Character {
    public int damage;
    public int health;
    Sprite sprite;
    boolean isDead;

    Character(Texture texture, int damage, int health)
    {
        sprite = new Sprite(texture);
        this.damage= damage;
        this.health = health;
        this.isDead = false;
    }
    public abstract void hit(int dmg);

}