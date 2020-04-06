package com.game.Entities.Pickups;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.game.Entities.Pickups.Item;
import com.game.Entities.Player;

public class Key extends Item {

    public Key(Player player, float posX, float posY) {
        super(player,posX,posY);
        //TODO: Change path to beer texture
        this.sprite = new Sprite(new Texture("Key.png"));
        this.player = player;
        this.type = "KEY";
        this.isPickedUp = false;
        this.sprite.setPosition(posX,posY);
    }
    @Override
    public void use() {

    }

    @Override
    public void end() {

    }
}
