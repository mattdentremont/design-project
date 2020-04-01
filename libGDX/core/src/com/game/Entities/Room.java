package com.game.Entities;

import com.badlogic.gdx.maps.tiled.TiledMap;

public class Room {
    public String mapName;
    public boolean hasBeenVisited;
    public boolean isDVDemon;
    public boolean isVHDL;

   public Room(String  map)
    {
        this.mapName = map;
        this.hasBeenVisited = false;
        this.isDVDemon = false;
        this.isVHDL = false;
    }

    public void setHasBeenVisited()
    {
        this.hasBeenVisited = true;
    }


}
