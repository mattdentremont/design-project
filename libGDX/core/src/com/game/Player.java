package com.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class Player {
    public int hp;//Health from 1-10
    Sprite sprite;
    boolean isDead;

    Player(Texture texture, int health)
    {
        sprite = new Sprite(texture);
        this.hp = health;
        this.isDead = false;
    }

    void damage(int owie)
    {
        this.hp = hp - owie;
        if(hp <= 0)
        {
            isDead = true;
        }
    }


}
