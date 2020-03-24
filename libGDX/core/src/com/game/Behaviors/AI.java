package com.game.Behaviors;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.game.Entities.Player;

public abstract class AI {

    AI(){
    }

    public abstract void move(Sprite enemy, Player player, float movementSpeed, float dt);

}
