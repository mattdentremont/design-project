package com.game.Behaviors;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.game.Entities.Enemy;
import com.game.Entities.Player;

public abstract class AI {

    AI(){
    }

    public abstract void move(Enemy enemy, Player player, float movementSpeed, float dt);

}
