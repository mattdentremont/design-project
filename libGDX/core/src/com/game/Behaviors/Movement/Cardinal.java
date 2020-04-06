package com.game.Behaviors.Movement;

import com.game.Entities.Enemies.Enemy;
import com.game.Entities.Player;
import com.game.main.escapeGame;

public class Cardinal extends AI {

    public Cardinal(){}

    @Override
    public void move(Enemy enemy, Player player, float movementSpeed, float dt) {
        float myX = enemy.getPosX();
        float myY = enemy.getPosY();
        float mul = movementSpeed*dt;

        int randInt = enemy.randRoll;

        if((randInt == 4) && (myY > 0)){
            enemy.setPosition(myX, myY - mul);
        }
        else if((randInt == 3) && (myY < (escapeGame.HEIGHT - enemy.sprite.getHeight()))){ //Up
            enemy.setPosition(myX, myY + mul);
        }
        else if((randInt == 2) && (myX < (escapeGame.WIDTH - enemy.sprite.getWidth()))){ //Right
            enemy.setPosition(myX + mul, myY);
        }
        else if((randInt==1) && (myX > 0)){
            enemy.setPosition(myX - mul, myY);
        }
        else {
            enemy.setPosition(myX, myY);
        }
    }
}
