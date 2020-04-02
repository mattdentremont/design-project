package com.game.Entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

public abstract class Character {
    public float speed;
    public int damage;
    public int health;
    public int posX;
    public int posY;
    public Sprite sprite;
    boolean isDead;
    private Texture texture;

    Character(String texturePath, float speed, int damage, int health,int posX, int posY)
    {
        this.texture = new Texture(Gdx.files.internal(texturePath));
        this.sprite = new Sprite(texture);
        this.speed = speed;
        this.damage= damage;
        this.health = health;
        this.posX = posX;
        this.posY = posY;
        this.isDead = false;
    }
    //public abstract void attack(Sprite player, String attackDirection);

}