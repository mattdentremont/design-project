package com.game.Managers;

import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.game.Entities.Player;
import com.game.Entities.Room;
import java.io.File;
import java.util.Random;

public class DungeonMapManager {
    private int width;
    private int height;
    private int xPos;
    private int yPos;
    private Room[][] dungeon;//matrix of dungeon rooms.
    private Room currentRoom;
    private String[] mapList;
    private Player player;

    public DungeonMapManager(String[] maps,int x, int y,Player player) {
        this.player = player;
        this.width = x;
        this.height = y;
        this.mapList = maps;
        dungeon = new Room[x][y];
        for (int i = 0; i < width; i++) {
                for(int j = 0; j < height; j++){
                int rand = new Random().nextInt(mapList.length);
                dungeon[i][j] = new Room(mapList[rand]);//assign random map to each dungeon room.
            }
        }
        this.xPos = x/2;
        this.yPos = y/2;
        setCurrentRoom(xPos,yPos,true);
    }

    public Room[][] getDungeon() {
        return dungeon;
    }

    public String[] getMaps() {
        return mapList;
    }

    public Room getCurrentRoom() {
        return currentRoom;
    }

    public int getxPos()
    {
        return this.xPos;
    }

    public int getHeight()
    {
        return this.height;
    }
    public int getWidth()
    {
        return this.width;
    }

    public int getyPos()
    {
        return this.yPos;
    }

    public void setCurrentRoom(int x, int y,boolean isFirst)
    {
        if(x>=0 && x < this.width && y>=0 && y < this.height) {
            currentRoom = dungeon[x][y];
            this.xPos = x;
            this.yPos = y;
            if(isFirst == true)
            {
                currentRoom.setHasBeenVisited();
            }
            if(currentRoom.hasBeenVisited == false)
            {
                player.incrementScore(10);
                currentRoom.setHasBeenVisited();
                player.incrementRoomsVisited();
                System.out.println("Score: "+ player.getScore() + " Health: "+ player.health+ " Rooms Visited: "+ player.roomsVisited);
                //10 for new room,50 for enemy,500
            }
        }
        else return;
    }


}




