package com.game.Entities;

import com.badlogic.gdx.graphics.g2d.Sprite;

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


}
