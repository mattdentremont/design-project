package com.game.Behaviors.Movement;

import com.game.Entities.Enemies.Enemy;
import com.game.Entities.Player;

public abstract class AI {

    public AI(){ }

    public abstract void move(Enemy enemy, Player player, float movementSpeed, float dt);

}
