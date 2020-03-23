package com.game.Managers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.game.Entities.Player;
import com.game.Entities.Room;
import com.game.main.escapeGame;

public class TiledMapManager {
    public static OrthographicCamera cam;
    private GameStateManager gsm;
    private escapeGame game;
    private Player player;

    public int WIDTH = 800;
    public int HEIGHT = 480;
    private Room currentRoom;
    private TmxMapLoader mapLoader;
    private TiledMap map;
    private OrthogonalTiledMapRenderer renderer;
    private DungeonMapManager dungeon;

    private MapLayer Doors;//doors to different rooms. may be null.



    public TiledMapManager(String mapPath,escapeGame game, Player player) {
        currentRoom = new Room(mapPath);
        this.game = game;
        this.player = player;
        cam = game.cam;
        WIDTH = game.WIDTH;
        HEIGHT = game.HEIGHT;
        mapLoader = new TmxMapLoader();
        map = mapLoader.load(currentRoom.mapName);
        renderer = new OrthogonalTiledMapRenderer(map);
        Doors = map.getLayers().get("Doors");

    }

    public void updateCam()
    {
        renderer.setView(cam);
    }

    public void render()
    {
        renderer.render();
    }

    public void changeRoom(String mapPath,String Direction)
    {
        map = mapLoader.load(mapPath);
        renderer = new OrthogonalTiledMapRenderer(map);
        Doors = map.getLayers().get("Doors");
        if(Direction == "UP") {
            player.setPosition(player.getPosX(), getDownDoorRectangle().height +5);
        }
        else if(Direction == "DOWN") {
            player.setPosition(player.getPosX(), getUpDoorRectangle().y-getUpDoorRectangle().height - 5);
        }
        else if(Direction == "LEFT") {
            player.setPosition(getRightDoorRectangle().x-getRightDoorRectangle().width -5, player.getPosY());
        }
        else if(Direction == "RIGHT") {
            player.setPosition(getLeftDoorRectangle().width +5, player.getPosY());
        }
    }

    public MapLayer getDoors()
    {
        return Doors;
    }

    public Rectangle getUpDoorRectangle()
    {
        MapObject UpDoor = Doors.getObjects().get("UpDoor");
        Rectangle UpRect = ((RectangleMapObject) UpDoor).getRectangle();
        return UpRect;
    }
    public Rectangle getDownDoorRectangle()
    {
        MapObject DownDoor = Doors.getObjects().get("DownDoor");
        Rectangle DownRect = ((RectangleMapObject) DownDoor).getRectangle();
        return DownRect;
    }
    public Rectangle getLeftDoorRectangle()
    {
        MapObject LeftDoor = Doors.getObjects().get("LeftDoor");
        Rectangle LeftRect = ((RectangleMapObject) LeftDoor).getRectangle();
        return LeftRect;
    }

    public Rectangle getRightDoorRectangle()
    {
        MapObject RightDoor = Doors.getObjects().get("RightDoor");
        Rectangle RightRect = ((RectangleMapObject) RightDoor).getRectangle();
        return RightRect;
    }


    public void checkDoors(DungeonMapManager dungeonMapManager)
    {
        if(dungeon == null)
        {
            this.dungeon = dungeonMapManager;
        }
        Rectangle upDoor = getUpDoorRectangle();
        Rectangle DownDoor = getDownDoorRectangle();
        Rectangle LeftDoor = getLeftDoorRectangle();
        Rectangle RightDoor = getRightDoorRectangle();

        if(player.sprite.getBoundingRectangle().overlaps(upDoor))
        {
            int currentX = dungeon.getxPos();
            int currentY = dungeon.getyPos();
            if(currentY +1 < dungeon.getHeight()) {
                dungeonMapManager.setCurrentRoom(currentX, currentY + 1);
                changeRoom(dungeonMapManager.getCurrentRoom().mapName,"UP");
            }
        }

        else if(player.sprite.getBoundingRectangle().overlaps(DownDoor))
        {
            int currentX = dungeon.getxPos();
            int currentY = dungeon.getyPos();
            if(currentY -1 >=0) {
                dungeonMapManager.setCurrentRoom(currentX, currentY - 1);
                changeRoom(dungeonMapManager.getCurrentRoom().mapName,"DOWN");
            }
        }

        else if(player.sprite.getBoundingRectangle().overlaps(LeftDoor))
        {
            int currentX = dungeon.getxPos();
            int currentY = dungeon.getyPos();
            if(currentX -1 >=0) {
                dungeonMapManager.setCurrentRoom(currentX - 1, currentY);
                changeRoom(dungeonMapManager.getCurrentRoom().mapName,"LEFT");
            }
        }

        else if(player.sprite.getBoundingRectangle().overlaps(RightDoor))
        {
            int currentX = dungeon.getxPos();
            int currentY = dungeon.getyPos();
            if(currentX +1 < dungeon.getWidth()) {
                dungeonMapManager.setCurrentRoom(currentX + 1, currentY);
                changeRoom(dungeonMapManager.getCurrentRoom().mapName,"RIGHT");
            }
        }
        else return;
    }



    public void dispose(){
        map.dispose();
        renderer.dispose();
    }


}
