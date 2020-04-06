package com.game.Entities.Enemies;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.game.Behaviors.Movement.Cardinal;
import com.game.Behaviors.Attacks.Shoot;
import com.game.Entities.Enemies.Enemy;
import com.game.Entities.Player;
import com.game.Entities.Projectile;

import java.util.ArrayList;
import java.util.Random;

/*
THIS CLASS IS NOT USED AS IT IS NOT FINISHED!
WE THOUGHT ABOUT TRYING TO ADD PROJECTILES EVEN THOUGH IT WASN'T IN THE ORIGINAL PLANS
AS WE HAD IMPLEMENTED EVERYTHING THAT WE WANTED TO AND WANTED A CHALLENGE!
 */

public class ProjectileEnemy extends Enemy {

    ArrayList<Projectile> projectiles;
    ArrayList<Projectile> temp;

    public ProjectileEnemy(float posX, float posY) {
        super(posX, posY, 0);
        this.sprite = new Sprite(new Texture(Gdx.files.internal("Bosses/V.png")));
        this.damageValue = 5;
        this.maxHealth = 30;
        this.movementSpeed = 100f;
        this.movementPattern = new Cardinal();
        this.attackPattern = new Shoot();
        this.currentHealth = this.maxHealth;
        this.posX = posX;
        this.posY = posY;
        this.isDead = false;
        this.sprite.setPosition(posX, posY);
        this.attackDelayCnt = 0;
        this.attackDelayTime = 1f;
        this.heading = 4;
        this.isBoss = true;
        this.hasProjectiles = true;
        this.projectiles = new ArrayList<>();
        this.temp = new ArrayList<>();
    }

    @Override
    public void attack(Player player, float dt) {
        this.attackDelayCnt += dt;
        if (this.attackDelayCnt >= this.attackDelayTime) {
            projectiles.add(this.attackPattern.shoot(player, this));//add new projectile to arraylist
            this.attackDelayCnt = 0;
        }
    }

    @Override
    public ArrayList<Projectile> getProjectiles(float dt)
    {
        if(dt == 0)
        {
            return this.projectiles;
        }
        else {
            for (Projectile x : projectiles) {
                x.update(dt);
            }
/*
             temp = projectiles;
            for(Projectile x : temp)
            {
                if(x.checkHit())
                    projectiles.remove(x);
            }*/
            return this.projectiles;
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
