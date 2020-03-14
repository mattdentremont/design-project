package com.game.Managers;

import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.game.Entities.Room;
import java.io.File;

public class DungeonMapManager {
    private int width;
    private int height;
    private Room[] dungeon;
    private Room currentRoom;
  //  private File[] mapFiles;
    private String[] maps;
   // private File dir;

    public DungeonMapManager(int x, int y) {
      /* this.width = x;
        this.height = y;
        File dir = new File("maps/rooms");
        mapFiles = dir.listFiles();
        maps = new TiledMap[mapFiles.length];
        if(mapFiles.length != 0) {
            for (int i = 0; i < mapFiles.length; i++) {
                maps[i] = "maps/map1.tmx";//TODO: Random maps from list
            }
            dungeon = new Room[x * y];
            for (int i = 0; i < width * height; i++) {
                Room room = new Room(maps[i]);
                dungeon[i] = room;
            }


        setCurrentRoom(width/2,height/2);*/
    }
    /*
    public Room[] getDungeon() {
        return dungeon;
    }

    public File getDir() {
        return dir;
    }

    public File[] getMapFiles() {
        return mapFiles;
    }

    public TiledMap[] getMaps() {
        return maps;
    }

    public Room getCurrentRoom() {
        return currentRoom;
    }

    public void setCurrentRoom(int x, int y)
    {
        currentRoom = dungeon[y*width+x];
    }
*/
}




