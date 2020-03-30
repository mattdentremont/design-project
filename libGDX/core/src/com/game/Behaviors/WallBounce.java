package com.game.Behaviors;

import com.game.Entities.Enemy;
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

        String heading = enemy.heading;
        if(myY <= 0f)
        {
            if (heading == "SE"){
                enemy.setPosition((myX + mul), myY + mul);
                enemy.heading = "NE";
            }
            else {
                enemy.setPosition((myX - mul), myY + mul);
                enemy.heading = "NW";
            }
        }
        else if (myY >= HEIGHT){
            if (heading == "NE"){
                enemy.setPosition((myX + mul), (myY - mul));
                enemy.heading = "SE";
            }
            else {
                enemy.setPosition((myX - mul), myY - mul);
                enemy.heading = "SW";
            }
        }
        else if (myX <= 0f)
        {
            if (heading == "NW"){
                enemy.setPosition((myX + mul), myY + mul);
                enemy.heading = "NE";
            }
            else {
                enemy.setPosition((myX + mul), myY - mul);
                enemy.heading = "SE";
            }
        }
        else { //myX >= WIDTH
            if (heading == "NE"){
                enemy.setPosition((myX - mul), myY + mul);
                enemy.heading = "NW";
            }
            else {
                enemy.setPosition((myX - mul), myY - mul);
                enemy.heading = "SW";
            }
        }
        if(enemy.heading == "NE"){
            enemy.setPosition((myX + mul), myY + mul);
        }
        else if(enemy.heading == "NW"){
            enemy.setPosition((myX - mul), myY + mul);
        }
        else if(enemy.heading == "SE"){
            enemy.setPosition((myX + mul), myY - mul);
        }
        else /*if(heading == "SW")*/{
            enemy.setPosition((myX + mul), myY + mul);
        }
//        else{
//            enemy.setPosition(0, 0);
//        }
    }
}
