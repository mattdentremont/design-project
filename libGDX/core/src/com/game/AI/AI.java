package com.game.AI;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.game.Entities.Player;

public abstract class AI {

    AI(){
    }

    public abstract void move(Sprite enemy, Player player, float movementSpeed);

}
