package com.game.States;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.game.Entities.Enemy;
import com.game.Entities.Player;
import com.game.Managers.GameStateManager;
import com.game.Managers.TiledMapManager;
import com.game.Managers.UI;
import com.game.main.escapeGame;

import java.util.ArrayList;


//Character select screen:
public class CharacterState extends GameState {

    private SpriteBatch sb;
    private BitmapFont font;
    private BitmapFont titleFont;
    private BitmapFont charsFont;
    private final String title = "Choose Your Fighter";
    private int currentItem;
    private String[] menuItems;
    private String[] damages;
    private String[] speeds;
    private String[] healths;
    private GlyphLayout layout;
    private Texture menuTexture;
    private Sprite menuSprite;
    private Texture p1Texture;
    private Sprite p1Sprite;
    private Texture p2Texture;
    private Sprite p2Sprite;
    private Music music;

    public CharacterState(GameStateManager gsm)
    {
        super(gsm);
        init();
        layout = new GlyphLayout();
    }

    @Override
    public void init() {

        sb = new SpriteBatch();
        charsFont = new BitmapFont();
        titleFont = new BitmapFont();
        font = new BitmapFont();
        titleFont.getData().setScale(3);
        charsFont.setColor(Color.WHITE);
        charsFont.getData().setScale(2);
        font.setColor(Color.WHITE);
        menuItems = new String[] {"The Student", "The Professor"};
        damages = new String[] {"Damage: 10", "Damage: 5"};
        speeds = new String[] {"Speed: 200", "Speed: 250"};
        healths = new String[] {"Health: 100", "Health: 150"};
        menuTexture = new Texture(Gdx.files.internal("menuBackground.jpg"));
        menuSprite = new Sprite(menuTexture);
        p1Texture = new Texture(Gdx.files.internal("TheStudentStill.png"));
        p1Sprite = new Sprite(p1Texture);
        p1Sprite.setPosition((escapeGame.WIDTH/3),  escapeGame.HEIGHT/2);
        p1Sprite.setScale(2f);
        p2Texture = new Texture(Gdx.files.internal("TheProf.png"));
        p2Sprite = new Sprite(p2Texture);
        p2Sprite.setPosition(2*(escapeGame.WIDTH/3),  escapeGame.HEIGHT/2);
        p2Sprite.setScale(2f);
        music = Gdx.audio.newMusic(Gdx.files.internal("sounds/menuMusic.mp3"));
        music.setLooping(true);
        music.setVolume(.2f);
        music.play();
    }

    @Override
    public void update(float dt)
    {
        handleInput(dt);
    }

    @Override
    public void draw() {
        sb.setProjectionMatrix(game.cam.combined);
        sb.begin();
        layout.setText(titleFont,title);
        float fontWidth = layout.width;

        //draw Title
        menuSprite.draw(sb);
        titleFont.draw(sb,title,((escapeGame.WIDTH-fontWidth)/2), (escapeGame.HEIGHT - 75));

        font.draw(sb, damages[0], escapeGame.WIDTH/3 -30, escapeGame.HEIGHT/2 - 25);
        font.draw(sb, damages[1], 2*escapeGame.WIDTH/3 -30, escapeGame.HEIGHT/2 - 25);
        font.draw(sb, speeds[0], escapeGame.WIDTH/3 -30, escapeGame.HEIGHT/2 - 50);
        font.draw(sb, speeds[1], 2*escapeGame.WIDTH/3 -30, escapeGame.HEIGHT/2 - 50);
        font.draw(sb, healths[0], escapeGame.WIDTH/3 -30, escapeGame.HEIGHT/2 - 75);
        font.draw(sb, healths[1], 2*escapeGame.WIDTH/3 -30, escapeGame.HEIGHT/2 - 75);
        p1Sprite.draw(sb);
        p2Sprite.draw(sb);

        //draw menu
        for(int i = 0; i < menuItems.length; i++){
            layout.setText(font, menuItems[i]);

            if(currentItem == i) {
                charsFont.setColor(Color.PURPLE);
            }
            else {
                charsFont.setColor(Color.WHITE);
            }
            float menuSpread = (((escapeGame.WIDTH/3)-60) + ((escapeGame.WIDTH/3)*i));
            charsFont.draw(sb, menuItems[i], menuSpread, 2*(escapeGame.HEIGHT/3));
        }
        font.draw(sb,"Press ESC To Return To Main Menu",escapeGame.WIDTH/2-120,50);
        sb.end();
    }

    @Override
    public void handleInput(float dt) {
        if(Gdx.input.isKeyJustPressed(Input.Keys.LEFT)){
            if(currentItem > 0) {
                currentItem--;
            }
        }
        if(Gdx.input.isKeyJustPressed(Input.Keys.RIGHT)){
            if (currentItem < menuItems.length - 1) {
                currentItem++;
            }
        }
        if(Gdx.input.isKeyJustPressed(Input.Keys.ENTER)) {
            charSelect();
        }
        if(Gdx.input.isKeyPressed(Input.Keys.ESCAPE)) {
            gsm.setState(GameStateManager.MENU);
        }
    }

    private void charSelect(){
        if(currentItem == 0) {
            gsm.setPlayState("TheStudent-Sheet.png", 200, 100, 10);

        }
        else if(currentItem == 1) {
            gsm.setPlayState("TheProf-Sheet.png", 250, 150, 5);

        }
    }

    @Override
    public void dispose() {
        sb.dispose();
        titleFont.dispose();
        font.dispose();
        music.dispose();
    }


    //Some accessors:
    @Override
    public TiledMapManager getmapManager() {
        return null;
    }

    @Override
    public ArrayList<Enemy> getEnemies() {
        return null;
    }

    @Override
    public Player getPlayer() {
        return null;
    }

    @Override
    public UI getHUD() {
        return null;
    }
}
