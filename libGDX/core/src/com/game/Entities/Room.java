package com.game.Entities;

import com.badlogic.gdx.maps.tiled.TiledMap;

public class Room {
    public String mapName;
    public boolean isEnemies;

   public Room(String  map)
    {
        this.mapName = map;
        this.isEnemies = true;
    }

}
