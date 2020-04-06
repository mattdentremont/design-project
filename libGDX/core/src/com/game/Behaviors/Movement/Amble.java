package com.game.Behaviors.Movement;

import com.game.Entities.Enemies.Enemy;
import com.game.Entities.Player;

import java.util.Random;

public class Amble extends AI {

    @Override
    public void move(Enemy enemy, Player player, float movementSpeed, float dt) {
        float targetX = player.getPosX();
        float targetY = player.getPosY();
        float myX = enemy.getPosX();
        float myY = enemy.getPosY();

        int randX = new Random().nextInt(2);
        int randY = new Random().nextInt(2);
        int randIndex = new Random().nextInt(5);

        if(randIndex == 0){
            enemy.setPosition(myX + randX, myY + randY);
        }
        else if(randIndex == 1){
            enemy.setPosition(myX + randX, myY - randY);
        }
        else if(randIndex == 2){
            enemy.setPosition(myX - randX, myY + randY);
        }
        else if(randIndex == 3){
            enemy.setPosition(myX - randX, myY - randY);
        }
        else if(randIndex == 4){
            enemy.setPosition(myX, myY);
        }

    }
}
