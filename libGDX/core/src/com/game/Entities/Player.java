package com.game.Entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class Player extends Character {


    Player(Texture texture, int damage, int health)
    {
        super(texture, damage, health);
    }

    @Override
    public void hit(int dmg) {
        this.health -= dmg;
        if(health <= 0){
            isDead = true;
        }
    }

}
