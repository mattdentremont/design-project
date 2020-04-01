package com.game.Managers;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.game.Entities.Item;
import com.game.Entities.Player;
import com.game.main.escapeGame;

public class UI {

    private Player player;
    private BitmapFont font;
    private GlyphLayout layout;
    private OrthographicCamera cam;
    private boolean hasBeer;
    private boolean hasRedBull;

    public UI(Player player, OrthographicCamera cam)
    {
        layout = new GlyphLayout();
        font = new BitmapFont();
        font.getData().setScale(1f);
        font.setColor(Color.PINK);
        this.cam = cam;
        this.player= player;
        this.hasBeer = false;
        this.hasRedBull = false;
    }


    public void draw(SpriteBatch sb)
    {
        String Score = Integer.toString(player.getScore());
        String Health = Integer.toString(player.getHealth());
        String roomsVisited = Integer.toString(player.getRoomsVisited());
        String enemiesDefeated = Integer.toString(player.getEnemiesDefeated());
        String toPrint = "Score:" + Score +" Health:" + Health ;
        String toPrint2 = "Rooms Visited:" + roomsVisited + " Enemies Defeated: " + enemiesDefeated;
        String toPrint3 = "Inventory: ";
        String empty = "Empty";

        //get Player Inventory
        Item[] playerInventory = player.getInventory();
        if(playerInventory[0] != null)
            this.hasBeer = true;
        else{
            this.hasBeer = false;
        }
        if(playerInventory[1] != null)
            this.hasRedBull = true;
        else{
            this.hasRedBull = false;
        }
        if(!hasBeer && !hasRedBull)
        {
            toPrint3 +=empty;
        }
        else if(hasBeer && !hasRedBull)
        {
            toPrint3 +="BEER";
        }
        else if(!hasBeer && hasRedBull)
        {
            toPrint3 +="REDBULL";
        }
        else if(hasBeer && hasRedBull)
        {
            toPrint3 +="BEER AND REDBULL";
        }


        layout.setText(font,toPrint);
        float fontWidth = layout.width;
        float fontHeight = layout.height;
        //draw title
        font.draw(sb,toPrint,0,escapeGame.HEIGHT - fontHeight);
        layout.setText(font,toPrint2);
        float fontWidth2 = layout.width;
        float fontHeight2 = layout.height;
        font.draw(sb,toPrint2,escapeGame.WIDTH-fontWidth2,escapeGame.HEIGHT - fontHeight2);
        layout.setText(font,toPrint3);
        float fontWidth3 = layout.width;
        float fontHeight3 = layout.height;
        font.draw(sb,toPrint3,0,escapeGame.HEIGHT - fontHeight3-15);
        }

        public void dispose()
        {
            font.dispose();
        }
}
