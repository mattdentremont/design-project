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
        player.speed = 400f;
        player.damage = 20;
    }

    @Override
    public void end() {
        player.speed = 200f;
        player.damage = 10;
    }
}