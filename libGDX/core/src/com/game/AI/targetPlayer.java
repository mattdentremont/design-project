package com.game.AI;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.game.Entities.Player;

public class targetPlayer extends AI{


    public targetPlayer(){
    }

    @Override
    public void move(Sprite enemy, Player player, float movementSpeed) {
        float targetX = player.sprite.getX();
        float targetY = player.sprite.getY();
        float myX = enemy.getX();
        float myY = enemy.getY();
        if(targetX < myX){
            enemy.setPosition(myX - movementSpeed, myY);
        }
        else{
            enemy.setPosition(myX + movementSpeed, myY);
        }
        if(targetY< myY){
            enemy.setPosition(myX, myY - movementSpeed);
        }
        else{
            enemy.setPosition(myX, myY + movementSpeed);
        }
    }
}
