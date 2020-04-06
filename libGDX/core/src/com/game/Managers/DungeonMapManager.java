package com.game.Managers;

import com.game.Entities.Player;
import com.game.Entities.Room;
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

        //Create new dungeon of size X * Y and populate with rooms and maps
        this.dungeon = new Room[x][y];
        for (int i = 0; i < width; i++) {
                for(int j = 0; j < height; j++){
                int rand = new Random().nextInt(mapList.length);
                this.dungeon[i][j] = new Room(mapList[rand]);//assign random map to each dungeon room.
            }
        }

        //Start in middle of the matrix
        this.xPos = x/2;
        this.yPos = y/2;

        //Set DVDemon Boss to appear in specific room
        int randxDVD = new Random().nextInt(x);
        int randyDVD = new Random().nextInt(y);
        while(xPos == randxDVD && yPos == randyDVD)//avoid dvdemon in first room
        {
             randxDVD = new Random().nextInt(x);
             randyDVD = new Random().nextInt(y);
        }
        dungeon[randxDVD][randyDVD].isDVDemon = true;

        //Set VHDL Boss to appear in specific room
        int randxVHDL = new Random().nextInt(x);
        int randyVHDL = new Random().nextInt(y);
        while(xPos == randxVHDL && yPos == randyVHDL ||randxDVD == randxVHDL && randyDVD == randyVHDL )//avoid first room & duplicate boss
        {
            randxVHDL = new Random().nextInt(x);
            randyVHDL= new Random().nextInt(y);
        }
        dungeon[randxVHDL][randyVHDL].isVHDL = true;

        //Start In Middle Room
        setCurrentRoom(xPos,yPos,true);
        currentRoom.setHasBeenVisited();
    }

    //Some Accessors
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

    //Change rooms when player enters a new one
    //X and Y are position in dungeonMap Matrix
    public void setCurrentRoom(int x, int y,boolean isFirst)
    {
        if(x>=0 && x < this.width && y>=0 && y < this.height) //If within dungeon matrix
        {
            if(currentRoom != null)
            {
                currentRoom.setHasBeenVisited();
            }

            currentRoom = dungeon[x][y];
            this.xPos = x;
            this.yPos = y;

            if(isFirst == true) //If this is the first/spawn room
            {
                currentRoom.setHasBeenVisited();
            }

            if(currentRoom.hasBeenVisited == false) //first time entering a new room
            {
                player.incrementScore(50);
                player.incrementRoomsVisited();
                System.out.println("Score: " + player.getScore() + " Health: " + player.health + " Rooms Visited: " + player.getRoomsVisited());
            }
        }
        else return; //Not Within Map Matrix, Return
    }


}




