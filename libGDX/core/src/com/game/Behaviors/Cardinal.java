package com.game.Behaviors;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.game.Behaviors.AI;
import com.game.Entities.Enemy;
import com.game.Entities.Player;
import com.game.main.escapeGame;

import java.util.Random;

public class Cardinal extends AI {

    public Cardinal(){

    }

    @Override
    public void move(Enemy enemy, Player player, float movementSpeed, float dt) {
        float myX = enemy.getPosX();
        float myY = enemy.getPosY();
        float mul = movementSpeed*dt;

        int randInt = enemy.randRoll;
        //Down?
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
