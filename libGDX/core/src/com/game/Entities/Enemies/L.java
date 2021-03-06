package com.game.Entities.Enemies;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.game.Behaviors.Animation;
import com.game.Behaviors.Movement.Cardinal;
import com.game.Behaviors.Attacks.Contact;
import com.game.Entities.Enemies.Enemy;
import com.game.Entities.Player;
import com.game.Entities.Projectile;

import java.util.ArrayList;
import java.util.Random;

public class L extends Enemy {

    public L(float posX, float posY) {
        super(posX, posY,0);
        this.texture = new Texture(Gdx.files.internal("Bosses/L-Sheet.png"));
        this.enemyAnimation = new Animation(new TextureRegion(texture),2,0.5f);
        this.sprite = new Sprite(enemyAnimation.getFrame());        this.damageValue = 20;
        this.maxHealth = 50;
        this.movementSpeed = 250f;
        this.movementPattern = new Cardinal();
        this.attackPattern = new Contact();
        this.currentHealth = this.maxHealth;
        this.posX = posX;
        this.posY = posY;
        this.isDead = false;
        this.sprite.setPosition(posX, posY);
        this.attackDelayCnt = 0;
        this.attackDelayTime = 0.5f;
        this.moveDelayCnt = 0;
        this.moveDelayTime = 0.5f;
        this.isBoss = true;
        this.hasAnimation = true;
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
        gotHitSound.play(1f);
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
    public ArrayList<Projectile> getProjectiles(float dt) {
        return null;
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
