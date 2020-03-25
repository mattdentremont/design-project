package com.game.Entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

public abstract class Character {
    public int damage;
    public int health;
    public int posX;
    public int posY;
    public Sprite sprite;
    boolean isDead;

    Character(Texture texture, int damage, int health,int posX, int posY)
    {
        this.sprite = new Sprite(texture);
        this.damage= damage;
        this.health = health;
        this.posX = posX;
        this.posY = posY;
        this.isDead = false;
    }
    //public abstract void attack(Sprite player, String attackDirection);

}