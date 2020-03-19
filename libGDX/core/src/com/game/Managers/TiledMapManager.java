package com.game.Managers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.game.Entities.Player;
import com.game.Entities.Room;
import com.game.main.escapeGame;

public class TiledMapManager {
    public static OrthographicCamera cam;
    private GameStateManager gsm;
    private escapeGame game;

    public int WIDTH = 800;
    public int HEIGHT = 480;
    private Room currentRoom;
    private TmxMapLoader mapLoader;
    private TiledMap map;
    private OrthogonalTiledMapRenderer renderer;


    public TiledMapManager(String mapPath,escapeGame game) {
        currentRoom = new Room(mapPath);
        this.game = game;
        cam = game.cam;
        WIDTH = game.WIDTH;
        HEIGHT = game.HEIGHT;
        mapLoader = new TmxMapLoader();
        map = mapLoader.load(currentRoom.mapName);
        renderer = new OrthogonalTiledMapRenderer(map);
    }

    public void update()
    {
        renderer.setView(cam);
    }

    public void render()
    {
        renderer.render();
    }

}
