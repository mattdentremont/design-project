package com.game.Entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.game.Animation.Animation;
import com.game.Behaviors.AI;
import com.game.Behaviors.Attack;

import java.util.ArrayList;

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
    public float moveDelayCnt;
    public float moveDelayTime;
    public int randRoll;
    public int heading;
    public boolean isBoss;
    public boolean hasProjectiles;
    public Sound gotHitSound = Gdx.audio.newSound(Gdx.files.internal("sounds/hitEnemy.mp3"));
    public Animation enemyAnimation;
    public Texture texture;
    public boolean hasAnimation;

    Enemy(float posX, float posY,double balancer)
    {
        this.posY = posY;
        this.posX = posX;
        this.isDead = false;
        this.flipSprite = false;
        this.isBoss = false;
        this.hasProjectiles = false;
        this.hasAnimation = false;
    }
    public abstract void attack(Player player, float dt);

    public abstract boolean takeDamage(int damageTaken);

    public abstract void move(Player player, float movementSpeed, float dt);
    public abstract ArrayList<Projectile> getProjectiles(float dt);

    public abstract float getPosX();

    public abstract float getPosY();
    public abstract boolean checkDead();
    public abstract void setPosition(float x,float y);
}
