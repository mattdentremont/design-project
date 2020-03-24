package com.game.Entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.game.Behaviors.AI;
import com.game.Behaviors.targetPlayer;

public class GreenBlob extends Enemy {

    public GreenBlob(Texture texture, int damageValue, int currentHealth, float posX, float posY)
    {
        super(texture, damageValue, currentHealth, posX, posY);//because damage and health scale with progression
        this.sprite = new Sprite(texture);
        this.damageValue = damageValue;
        this.currentHealth = currentHealth;
        this.posX = posX;
        this.posY = posY;
        this.isDead = false;
        this.sprite.setPosition(posX, posY);
    }

    float movementSpeed = 100f; //Probably make me bigger
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
    public void move(Player player, float movementSpeed, float dt) {
        this.movementPattern.move(this.sprite, player, this.movementSpeed, dt);
    }

}
