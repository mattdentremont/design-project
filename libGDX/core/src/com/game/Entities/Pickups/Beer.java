package com.game.Entities.Pickups;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.game.Entities.Pickups.Item;
import com.game.Entities.Player;

public class Beer extends Item {
    public Beer(Player player, float posX, float posY) {
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
        player.maxHealth = 150;
        player.health = 150;
    }

    @Override
    public void end() {
        player.maxHealth = 100;
        if(player.health >100)
            player.health = 100;

    }
}
