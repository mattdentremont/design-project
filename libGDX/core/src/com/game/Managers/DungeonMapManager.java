package com.game.Managers;

import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.game.Entities.Room;
import java.io.File;

public class DungeonMapManager {
    private OrthogonalTiledMapRenderer tmr;
    private Room[] dungeon;
    private File[] mapFiles;
    private TiledMap[] maps;
    File dir;

    DungeonMapManager()
    {
        File dir = new File("maps/rooms");
        mapFiles = dir.listFiles();

        for(int i = 0; i < mapFiles.length; i++ )
        {
            maps[i] = new TmxMapLoader().load(mapFiles[i].getName());
        }
        for(int i = 0; i < 25; i++){
            Room room = new Room(maps[i]);
            dungeon[i] = room;
        }
        }
}


