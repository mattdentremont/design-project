package com.game.Entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class Beer extends Item {
    public Beer(Player player,float posX,float posY) {
        super(player,posX,posY);
        //TODO: Change path to beer texture
        this.sprite = new Sprite(new Texture("Beer.png"));
        this.player = player;
        this.type = "BEER";
        this.sprite.setPosition(posX,posY);
        this.isPickedUp = false;
    }

    @Override
    public void use() {
        //increase player's max health
        //set health to this value
        //decrease back to normal after a time period (e.g. 10s)
    }
}
