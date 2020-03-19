package com.game.Entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class GreenBlob extends Enemy {

    GreenBlob(Texture texture, int damageValue, int currentHealth, int movementSpeed) {
        super(texture, damageValue, currentHealth); //because damage and health scale with progression
    }

    float movementSpeed = 1; //Probably make me bigger

    @Override
    public void attack(Player player) {
        player.health -= this.damageValue;
        if (player.health <= 0){
            player.isDead = true;
        }
    }

    @Override
    public void takeDamage(int damageTaken) {
        this.currentHealth -= damageTaken;
        if (this.currentHealth <= 0){
            isDead = true;
        }
    }

    @Override
    public void move(Sprite target) {
        float targetX = target.getX();
        float targetY = target.getY();
        float myX = this.sprite.getX();
        float myY = this.sprite.getY();
        if(targetX < myX){
            this.sprite.setPosition(myX - this.movementSpeed, myY);
        }
        else{
            this.sprite.setPosition(myX + this.movementSpeed, myY);
        }
        if(targetY< myY){
            this.sprite.setPosition(myX, myY - this.movementSpeed);
        }
        else{
            this.sprite.setPosition(myX, myY + this.movementSpeed);
        }
    }
}
