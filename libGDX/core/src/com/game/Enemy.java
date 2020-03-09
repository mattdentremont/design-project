package com.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class Enemy {
    public int damage;//Health from 1-10
    Sprite sprite;

    Enemy(Texture texture, int hurts)
    {
        sprite = new Sprite(texture);
        this.damage= hurts;
    }




}