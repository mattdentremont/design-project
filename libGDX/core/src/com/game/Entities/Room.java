package com.game.Entities;

import com.badlogic.gdx.maps.tiled.TiledMap;

public class Room {
    public String mapName;
    public boolean isEnemies;
    public Enemy[] enemies;
    public boolean hasBeenVisited;

   public Room(String  map)
    {
        this.mapName = map;
        this.isEnemies = true;
        this.hasBeenVisited = false;
    }

    public void setHasBeenVisited()
    {
        this.hasBeenVisited = true;
    }
    public void setIsEnemies()
    {
        this.isEnemies = false;
    }

    public Enemy[] getEnemies()
    {
        return enemies;
    }

}
