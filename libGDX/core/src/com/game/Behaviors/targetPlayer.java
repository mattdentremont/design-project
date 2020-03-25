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

        if(targetX < myX && ((myX - targetX ) > 3)){
            if(targetY > myY && ((targetY - myY ) > 3))
                enemy.setPosition(myX - (float) (.7071) *(movementSpeed*dt),myY + (float) (.7071) *(movementSpeed*dt));
            else if(targetY< myY && ((myY - targetY) > 3))
                enemy.setPosition( myX - (float) (.7071) *(movementSpeed*dt),myY - (float) (.7071) *(movementSpeed*dt));
            else enemy.setPosition(myX - (movementSpeed*dt), myY);
        }
        else if(targetX > myX &&((targetX - myX ) > 3)){
            if(targetY > myY && ((targetY - myY ) > 3))
                enemy.setPosition(myX + (float) (.7071) *(movementSpeed*dt),myY + (float) (.7071) *(movementSpeed*dt));
            else if(targetY< myY && ((myY - targetY) > 3))
                enemy.setPosition(myX + (float) (.7071) *(movementSpeed*dt),myY - (float) (.7071) *(movementSpeed*dt));
            else enemy.setPosition(myX + (movementSpeed*dt), myY);
        }
        else if(targetY > myY && ((targetY - myY ) > 3))
            enemy.setPosition(myX,myY + (movementSpeed*dt));
        else if(targetY< myY && ((myY - targetY) > 3))
            enemy.setPosition(myX,myY - (movementSpeed*dt));

    }
}
