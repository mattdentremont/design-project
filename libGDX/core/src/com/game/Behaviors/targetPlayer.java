package com.game.Behaviors;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.game.Entities.Player;

public class targetPlayer extends AI{


    public targetPlayer(){
    }

    @Override
    public void move(Sprite enemy, Player player, float movementSpeed, float dt) {
        float targetX = player.sprite.getX();
        float targetY = player.sprite.getY();
        float myX = enemy.getX();
        float myY = enemy.getY();

        //either change to vectors or nested ifs for diagonals and smoother moving.
        //or just keep as cardinal???

        if(targetX < myX){
            enemy.setPosition(myX - (movementSpeed*dt), myY);
        }
        if(targetX > myX){
            enemy.setPosition(myX + (movementSpeed*dt), myY);
        }
        if(targetY< myY && ((myY - targetY) > 3)){
            enemy.setPosition(myX, myY - (movementSpeed*dt));
        }
        if(targetY > myY && ((targetY - myY ) > 3)){
            enemy.setPosition(myX, myY + (movementSpeed*dt));
        }
    }
}
