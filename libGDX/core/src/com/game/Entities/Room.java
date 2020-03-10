package com.game.Entities;

import com.badlogic.gdx.maps.tiled.TiledMap;

public class Room {
    public TiledMap map;
    public boolean isEnemies;

   public Room(TiledMap lvl)
    {
        this.map = lvl;
        this.isEnemies = true;
    }

}
