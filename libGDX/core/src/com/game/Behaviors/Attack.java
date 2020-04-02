package com.game.Behaviors;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.game.Entities.Enemy;
import com.game.Entities.Player;
import com.game.Entities.Projectile;

public abstract class Attack {

    public Attack(){
    }

    public abstract void attack(Player player, Enemy attacker);
    public abstract Projectile shoot(Player player, Enemy attacker);

    //public abstract boolean attackDelay(float delay, float dt);
}
