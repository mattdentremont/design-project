package com.game.Managers;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.game.Entities.Player;
import com.game.main.escapeGame;

public class UI {

    private Player player;
    private BitmapFont font;
    private GlyphLayout layout;
    private OrthographicCamera cam;

    public UI(Player player, OrthographicCamera cam)
    {
        layout = new GlyphLayout();
        font = new BitmapFont();
        font.getData().setScale(1f);
        font.setColor(Color.BLACK);
        this.cam = cam;
        this.player= player;
    }


    public void draw(SpriteBatch sb)
    {
        String Score = Integer.toString(player.getScore());
        String Health = Integer.toString(player.getHealth());
        String roomsVisited = Integer.toString(player.getRoomsVisited());
        String enemiesDefeated = Integer.toString(player.getEnemiesDefeated());

        String toPrint = "Score:" + Score +" Health:" + Health ;
        String toPrint2 = "Rooms Visited:" + roomsVisited + " Enemies Defeated: " + enemiesDefeated;

        layout.setText(font,toPrint);
        float fontWidth = layout.width;
        float fontHeight = layout.height;
        //draw title
        font.draw(sb,toPrint,0,escapeGame.HEIGHT - fontHeight);
        layout.setText(font,toPrint2);
        float fontWidth2 = layout.width;
        float fontHeight2 = layout.height;
        font.draw(sb,toPrint2,escapeGame.WIDTH-fontWidth2,escapeGame.HEIGHT - fontHeight2);
        }

        public void dispose()
        {
            font.dispose();
        }
}
