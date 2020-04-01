package com.game.Entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class RedBull extends Item {
    public RedBull(Player player, float posX, float posY) {
        super(player,posX,posY);
        //TODO: Change path to beer texture
        this.sprite = new Sprite(new Texture("Redbull.png"));
        this.player = player;
        this.type = "REDBULL";
        this.isPickedUp = false;
        this.sprite.setPosition(posX,posY);
    }

    @Override
    public void use() {
        //increase player's max health
        //set health to this value
        //decrease back to normal after a time period (e.g. 10s)
    }
}