package com.game.Entities.Pickups;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.game.Entities.Player;

//Abstract class for items that can be picked up in the game.
public abstract class Item {
    public Sprite sprite;
    public Player player;
    public String type;
    public boolean isPickedUp;
    public float posX;
    public float posY;

    Item(Player player,float posX, float posY)
    {
        this.player = player;
        this.posX = posX;
        this.posY = posY;
        this.isPickedUp = false;

    }

    //use to set position in front of player when picking up new item.
    public void setPosition(float posX,float posY)
    {
        this.posX = posX;
        this.posY = posY;
        this.sprite.setPosition(posX,posY);
    }

    public void setPickedUp(boolean x)
    {
        this.isPickedUp = x;
    }

    public boolean checkPickedUp()
    {
        return this.isPickedUp;
    }


    public abstract void use();
    public abstract void end();


}
