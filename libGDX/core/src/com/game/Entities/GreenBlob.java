package com.game.Entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.game.AI.AI;
import com.game.AI.targetPlayer;

public class GreenBlob extends Enemy {

    GreenBlob(Texture texture, int damageValue, int currentHealth, int movementSpeed) {
        super(texture, damageValue, currentHealth); //because damage and health scale with progression
    }

    float movementSpeed = 1; //Probably make me bigger
    AI movementPattern =  new targetPlayer();

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
    public void move(Player player, int movementSpeed) {
        this.movementPattern.move(this.sprite, player, this.movementSpeed);
    }

}
