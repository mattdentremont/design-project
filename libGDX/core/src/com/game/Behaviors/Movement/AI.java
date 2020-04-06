package com.game.Behaviors.Movement;

import com.game.Entities.Enemies.Enemy;
import com.game.Entities.Player;

//Abstract class that allows movement patterns to be defined. Enemies can move differently by having different movement patterns.
public abstract class AI {

    public AI(){
    }

    public abstract void move(Enemy enemy, Player player, float movementSpeed, float dt);

}
