package com.game.Behaviors.Movement;

import com.game.Entities.Enemies.Enemy;
import com.game.Entities.Player;

public class targetPlayer extends AI {


    public targetPlayer(){ }

    @Override
    public void move(Enemy enemy, Player player, float movementSpeed, float dt) {
        float targetX = player.getPosX();
        float targetY = player.getPosY();
        float myX = enemy.getPosX();
        float myY = enemy.getPosY();

        if(targetX < myX && ((myX - targetX ) > 3)){ //If Target is on the left
            if(targetY > myY && ((targetY - myY ) > 3))//Up & Left
                enemy.setPosition(myX - (float) (.7071) *(movementSpeed*dt),myY + (float) (.7071) *(movementSpeed*dt)); //Math to keep same speed diagonally
            else if(targetY< myY && ((myY - targetY) > 3))//Down & Left
                enemy.setPosition( myX - (float) (.7071) *(movementSpeed*dt),myY - (float) (.7071) *(movementSpeed*dt));
            else enemy.setPosition(myX - (movementSpeed*dt), myY); //Move Directly Left
        }
        else if(targetX > myX &&((targetX - myX ) > 3)){ //If target is on the Right
            if(targetY > myY && ((targetY - myY ) > 3)) //Up & Right
                enemy.setPosition(myX + (float) (.7071) *(movementSpeed*dt),myY + (float) (.7071) *(movementSpeed*dt));
            else if(targetY< myY && ((myY - targetY) > 3)) //Down & Right
                enemy.setPosition(myX + (float) (.7071) *(movementSpeed*dt),myY - (float) (.7071) *(movementSpeed*dt));
            else enemy.setPosition(myX + (movementSpeed*dt), myY);// Directly Right
        }
        else if(targetY > myY && ((targetY - myY ) > 3))
            enemy.setPosition(myX,myY + (movementSpeed*dt));//Directly Up
        else if(targetY< myY && ((myY - targetY) > 3))
            enemy.setPosition(myX,myY - (movementSpeed*dt));//Directly Down

    }
}
