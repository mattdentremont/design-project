package com.game.Entities;

import com.badlogic.gdx.maps.tiled.TiledMap;

//Class for a room. This defines what type of enemies it has and the tiled map path. Also tracks if it has been visited by the player.
public class Room {
    public String mapName;
    public boolean hasBeenVisited;
    public boolean isDVDemon;
    public boolean isVHDL;
    public boolean isProjectileEnemy;

   public Room(String  map)
    {
        this.mapName = map;
        this.hasBeenVisited = false;
        this.isDVDemon = false;
        this.isVHDL = false;
        this.isProjectileEnemy = false;
    }

    public void setHasBeenVisited()
    {
        this.hasBeenVisited = true;
    }


}
