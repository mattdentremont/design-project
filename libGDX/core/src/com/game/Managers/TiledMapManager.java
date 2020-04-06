package com.game.Managers;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.MapObjects;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.game.Entities.*;
import com.game.main.escapeGame;
import java.util.ArrayList;
import java.util.Random;

//Class to manage current rooms tiled map and enemies/items spawning:
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
    private MapLayer EnemySpawns;
    private ArrayList<Enemy> enemies;
    private ArrayList<Item> items;
    private boolean spawnedKey;
    private Sound doorSound = Gdx.audio.newSound(Gdx.files.internal("sounds/door_lock.mp3"));



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
        EnemySpawns = map.getLayers().get("EnemySpawns");
        enemies = new ArrayList<>();
        items = new ArrayList<>();
        spawnedKey = false;
        //Enemies are not spawned in the first room!
    }

    //Update gamecam
    public void updateCam()
    {
        renderer.setView(cam);
    }

    //render current map
    public void render()
    {
        renderer.render();
    }

    //Called when a player goes through a door and changes rooms:
    public void changeRoom(String mapPath,String Direction)
    {
        doorSound.play(.5f);
        items = new ArrayList<>();
        map = mapLoader.load(mapPath);
        renderer = new OrthogonalTiledMapRenderer(map);
        Doors = map.getLayers().get("Doors");
        EnemySpawns = map.getLayers().get("EnemySpawns");
        if(dungeon.getCurrentRoom().hasBeenVisited == false) {
            //set flags:
            player.setKey(false);
            spawnedKey = false;

            //spawn items:
            spawnItems();

            //Figure out what enemies are in this room and spawn them accordingly:
            if(dungeon.getCurrentRoom().isDVDemon)
            {
                spawnDVDemon();//spawn the DVDemon boss.
            }
            else if(dungeon.getCurrentRoom().isVHDL)
            {
                spawnVHDL();//spawn the VHDL boss.
            }
            else {
                    int statIncrease = (int)Math.floor(player.getRoomsVisited()/10.0)*5;//every 10 rooms enemies gain 5 for each stat.
                    spawnEnemies(statIncrease);//spawn normal enemies.
            }
        }

        //Check what door you went through and adjust position in the next room accordingly:
        if(Direction == "UP") {
            player.setPosition(getDownDoorRectangle().x + getDownDoorRectangle().width/2, getDownDoorRectangle().height +20);
        }
        else if(Direction == "DOWN") {
            player.setPosition(getUpDoorRectangle().x + getUpDoorRectangle().width/2, getUpDoorRectangle().y-getUpDoorRectangle().height - 20);
        }
        else if(Direction == "LEFT") {
            player.setPosition(getRightDoorRectangle().x-getRightDoorRectangle().width -20, getRightDoorRectangle().y + getRightDoorRectangle().height/2);
        }
        else if(Direction == "RIGHT") {
            player.setPosition(getLeftDoorRectangle().width+getRightDoorRectangle().width +20, getLeftDoorRectangle().y + getLeftDoorRectangle().height/2);
        }
    }


    //spawn enemies
    public void spawnEnemies(int balancer)
    {
        MapObjects spawnLocations = EnemySpawns.getObjects();
        for(RectangleMapObject location: spawnLocations.getByType(RectangleMapObject.class))
        {
           float xPos =  location.getRectangle().getX();
           float yPos = location.getRectangle().getY();
            int rand = new Random().nextInt(4);
            if(rand == 0) {
                enemies.add(new Wanderers(xPos,yPos,balancer));
            }
            else{
                enemies.add(new GreenBlob(xPos, yPos, balancer));
            }
        }
    }


    //spawn DVDemon boss:
    public void spawnDVDemon()
    {
        enemies.add(new DVDemon(WIDTH/3, HEIGHT/2));
    }

    //Spawn VHDL boss:
    public void spawnVHDL()
    {
        enemies.add(new V(WIDTH/2, 2*HEIGHT/3));
        enemies.add(new H(2*WIDTH/3, HEIGHT/2));
        enemies.add(new D(WIDTH/2, HEIGHT/3));
        enemies.add(new L(WIDTH/3, HEIGHT/2));
    }

    //spawn items:
    public void spawnItems()
    {
        MapObjects spawnLocations = EnemySpawns.getObjects();
        for(RectangleMapObject location: spawnLocations.getByType(RectangleMapObject.class))
        {
            int rand = new Random().nextInt(9);//random number from 0-9 inclusive.
            int randItem = new Random().nextInt(2);//random number from 0-1 inclusive.
            if(rand == 0) {//10% chance of item spawning
                float xPos = location.getRectangle().getX();
                float yPos = location.getRectangle().getY();
                if(randItem == 0)//50% chance for beer, 50% chance for GFuel.
                    items.add(new Beer(player,xPos, yPos));
                else
                    items.add(new RedBull(player,xPos,yPos));
            }
        }
    }




    //Check if player hit a door and if they can unlock that door, if so the room will be changed:
    public void checkDoors(DungeonMapManager dungeonMapManager)
    {
        //If no enemies and you have a key for this room you can leave.
        if(enemies.size() == 0 && player.checkKey()) {
            if (dungeon == null) {
                this.dungeon = dungeonMapManager;
            }
            //get door objects from tiled map
            Rectangle upDoor = getUpDoorRectangle();
            Rectangle DownDoor = getDownDoorRectangle();
            Rectangle LeftDoor = getLeftDoorRectangle();
            Rectangle RightDoor = getRightDoorRectangle();

            //Check for each door if the player is trying to leave then change room accordingly:
            if (player.sprite.getBoundingRectangle().overlaps(upDoor)) {
                int currentX = dungeon.getxPos();
                int currentY = dungeon.getyPos();
                if (currentY + 1 < dungeon.getHeight()) {
                    dungeonMapManager.setCurrentRoom(currentX, currentY + 1, false);
                    changeRoom(dungeonMapManager.getCurrentRoom().mapName, "UP");
                }
            } else if (player.sprite.getBoundingRectangle().overlaps(DownDoor)) {
                int currentX = dungeon.getxPos();
                int currentY = dungeon.getyPos();
                if (currentY - 1 >= 0) {
                    dungeonMapManager.setCurrentRoom(currentX, currentY - 1, false);
                    changeRoom(dungeonMapManager.getCurrentRoom().mapName, "DOWN");
                }
            } else if (player.sprite.getBoundingRectangle().overlaps(LeftDoor)) {
                int currentX = dungeon.getxPos();
                int currentY = dungeon.getyPos();
                if (currentX - 1 >= 0) {
                    dungeonMapManager.setCurrentRoom(currentX - 1, currentY, false);
                    changeRoom(dungeonMapManager.getCurrentRoom().mapName, "LEFT");
                }
            } else if (player.sprite.getBoundingRectangle().overlaps(RightDoor)) {
                int currentX = dungeon.getxPos();
                int currentY = dungeon.getyPos();
                if (currentX + 1 < dungeon.getWidth()) {
                    dungeonMapManager.setCurrentRoom(currentX + 1, currentY, false);
                    changeRoom(dungeonMapManager.getCurrentRoom().mapName, "RIGHT");
                }
            } else return;
        }

        //if enemies are dead and you don't have the key spawn it:
        else if(enemies.size() == 0 &&!spawnedKey){
            items.add(new Key(player,WIDTH/2-40,HEIGHT/2));
            spawnedKey = true;
        }
    }

    //Accessors:
    public ArrayList<Enemy> getEnemyList()
    {
        return enemies;
    }

    public ArrayList<Item> getItemList()
    {
        return items;
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

    public void dispose(){
        map.dispose();
        renderer.dispose();
        doorSound.dispose();
    }


}
