package com.game.Managers;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.game.Entities.Pickups.Item;
import com.game.Entities.Player;
import com.game.main.escapeGame;

//Class to draw heads up display with important info:
public class UI {

    private Player player;
    private BitmapFont font;
    private GlyphLayout layout;
    private OrthographicCamera cam;
    private boolean hasBeer;
    private boolean hasRedBull;
    private GameInputProcessor ip;

    public UI(Player player, OrthographicCamera cam,GameInputProcessor ip)
    {
        layout = new GlyphLayout();
        font = new BitmapFont();
        font.getData().setScale(1f);
        font.setColor(Color.WHITE);
        this.ip = ip;
        this.cam = cam;
        this.player= player;
        this.hasBeer = false;
        this.hasRedBull = false;
    }

    //Draw values to screen:
    public void draw(SpriteBatch sb)
    {
        String Score = Integer.toString(player.getScore());
        String Health = Integer.toString(player.getHealth());
        String roomsVisited = Integer.toString(player.getRoomsVisited());
        String enemiesDefeated = Integer.toString(player.getEnemiesDefeated());
        String toPrint = "Score:" + Score +" Health:" + Health;
        String toPrint2 = "Rooms Visited:" + roomsVisited + " Enemies Defeated: " + enemiesDefeated;
        String toPrint3 = "Inventory: ";
        String empty = "Empty";
        String Ult = "Ultimate Charge(F):" + Integer.toString((int)player.getUltCharge()) + "%";

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
            toPrint3 +="BEER(Q)";
        }
        else if(!hasBeer && hasRedBull)
        {
            toPrint3 +="REDBULL(E)";
        }
        else if(hasBeer && hasRedBull)
        {
            toPrint3 +="BEER(Q) AND REDBULL(E)";
        }


        //Draw score, health, rooms visited and enemies defeated:
        layout.setText(font,toPrint);
        float fontHeight = layout.height;
        font.draw(sb,toPrint,0,escapeGame.HEIGHT - fontHeight/2);
        layout.setText(font,toPrint2);
        float fontWidth2 = layout.width;
        float fontHeight2 = layout.height;
        font.draw(sb,toPrint2,escapeGame.WIDTH-fontWidth2,escapeGame.HEIGHT - fontHeight2/2);
        layout.setText(font,toPrint3);
        float fontHeight3 = layout.height;
        font.draw(sb,toPrint3,0,escapeGame.HEIGHT - fontHeight3-15);

        //draw ult percentage
        layout.setText(font,Ult);
        float fontWidthUlt = layout.width;
        float fontHeightUlt = layout.height;
        font.draw(sb,Ult,escapeGame.WIDTH/2-fontWidthUlt/2,escapeGame.HEIGHT - fontHeightUlt/2);

        //Print Counters For consumable effects
        if(ip.checkUsedBeer() || ip.checkUsedRedBull())
        {
            String BeerCounter = "Increased Health:" +Integer.toString((int)(ip.getBeerEffectLength()-(int)ip.getBeerTimer()));
            String RedBullCounter = "Jacked Up:" + Integer.toString((int)(ip.getRedBullEffectLength()-(int)ip.getRedBullTimer()));
            float fontWidthBeer;
            float fontHeightBeer;
            float fontWidthRedBull;
            float fontHeightRedBull;
            if(ip.checkUsedBeer() && ip.checkUsedRedBull()) {
                layout.setText(font, BeerCounter);
                fontWidthBeer = layout.width;
                fontHeightBeer = layout.height;
                font.draw(sb, BeerCounter, escapeGame.WIDTH - fontWidthBeer, escapeGame.HEIGHT - fontHeightBeer - 15);
                layout.setText(font, BeerCounter);

                layout.setText(font, RedBullCounter);
                fontWidthRedBull = layout.width;
                fontHeightRedBull = layout.height;
                font.draw(sb, RedBullCounter, escapeGame.WIDTH - fontWidthRedBull, escapeGame.HEIGHT - fontHeightRedBull - 30);
                layout.setText(font, BeerCounter);
            }
            else if(ip.checkUsedBeer() && !ip.checkUsedRedBull()) {
                layout.setText(font, BeerCounter);
                fontWidthBeer = layout.width;
                fontHeightBeer = layout.height;
                font.draw(sb, BeerCounter, escapeGame.WIDTH - fontWidthBeer, escapeGame.HEIGHT - fontHeightBeer - 15);
                layout.setText(font, BeerCounter);

            }
            else if(!ip.checkUsedBeer() && ip.checkUsedRedBull()) {
                layout.setText(font, RedBullCounter);
                fontWidthRedBull = layout.width;
                fontHeightRedBull = layout.height;
                font.draw(sb, RedBullCounter, escapeGame.WIDTH - fontWidthRedBull, escapeGame.HEIGHT - fontHeightRedBull - 15);
                layout.setText(font, BeerCounter);

            }
        }
        }

        public void dispose()
        {
            font.dispose();
        }
}
