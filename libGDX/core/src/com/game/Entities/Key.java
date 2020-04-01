package com.game.Entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class Key extends Item {

    public Key(Player player,float posX,float posY) {
        super(player,posX,posY);
        //TODO: Change path to beer texture
        this.sprite = new Sprite(new Texture("Key.png"));
        this.player = player;
        this.type = "KEY";
        this.sprite.setPosition(posX,posY);
    }
    @Override
    public void use() {

    }

    @Override
    public void end() {

    }
}
