package com.game.Entities;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;

/*
THIS CLASS IS NOT USED ANYWHERE AS IT IS NOT FINISHED!
WE THOUGHT ABOUT TRYING TO ADD PROJECTILES EVEN THOUGH IT WASN'T IN THE ORIGINAL PLANS
AS WE HAD IMPLEMENTED EVERYTHING THAT WE WANTED TO AND WANTED A CHALLENGE!
 */

public class Projectile {
    public int damageValue;
    public float movementSpeed;
    private float currentPosx;
    private float currentPosy;
    private boolean didHit;
    public Sprite sprite;
    private Vector2 path;
    private Player player;

    public Projectile(Player player,float posX, float posY)
    {
        sprite = new Sprite(new Texture("bullet.png"));
        float targetX = player.getPosX();
        float targetY = player.getPosY();
        path = new Vector2(posX - targetX,targetY-posY);
        this.damageValue = 10;
        this.movementSpeed = 50f;
        this.player =player;
        this.didHit = false;

    }

    public void update(float dt)
    {
        path.add(path.x +movementSpeed*dt,path.y + movementSpeed*dt );
        currentPosx = path.x;
        currentPosy = path.y;
        sprite.setPosition(currentPosx,currentPosy);
        if(currentPosx <= 0 || currentPosx >= 800 || currentPosy <= 0 || currentPosy >=480) {
            didHit = true;
        }
        else if(player.sprite.getBoundingRectangle().overlaps(this.sprite.getBoundingRectangle())){
            player.gotHit(this.damageValue);
            didHit = true;
        }

    }

    public boolean checkHit()
    {
        return didHit;
    }

}
