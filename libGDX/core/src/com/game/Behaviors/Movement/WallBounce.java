package com.game.Behaviors.Movement;

import com.game.Entities.Enemies.Enemy;
import com.game.Entities.Player;
import static com.game.main.escapeGame.WIDTH;
import static com.game.main.escapeGame.HEIGHT;

public class WallBounce extends AI
{
    public WallBounce(){}

    @Override //TODO: Add get and set heading methods
    public void move(Enemy enemy, Player player, float movementSpeed, float dt) {
        float myX = enemy.getPosX();
        float myY = enemy.getPosY();
        float mul = (movementSpeed*dt);

        int heading = enemy.heading;
        if(myY <= 0f)
        {
            if (heading == 4){
                enemy.heading = 1;
            }
            else {
                enemy.heading = 2;
            }
        }
        else if (myY + enemy.sprite.getHeight() >= HEIGHT){
            if (heading == 1){
                enemy.heading = 4;
            }
            else {
                enemy.heading = 3;
            }
        }
        else if (myX <= 0f)
        {
            if (heading == 2){
                enemy.heading = 1;
            }
            else {
                enemy.heading = 4;
            }
        }
        else if (myX + enemy.sprite.getWidth() >= WIDTH){ //myX >= WIDTH
            if (heading == 1){
                enemy.heading = 2;
            }
            else {
                enemy.heading = 3;
            }
        }
        if(enemy.heading == 1){
            enemy.setPosition((myX + mul), myY + mul);
        }
        else if(enemy.heading == 2){
            enemy.setPosition((myX - mul), myY + mul);
        }
        else if(enemy.heading == 4){
            enemy.setPosition((myX + mul), myY - mul);
        }
        else if(heading == 3){
            enemy.setPosition((myX - mul), myY - mul);
        }
    }
}
