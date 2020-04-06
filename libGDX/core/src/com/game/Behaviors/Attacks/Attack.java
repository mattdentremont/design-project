package com.game.Behaviors.Attacks;

import com.game.Entities.Enemies.Enemy;
import com.game.Entities.Player;
import com.game.Entities.Projectile;

public abstract class Attack {

    public Attack(){
    }

    public abstract void attack(Player player, Enemy attacker);
    public abstract Projectile shoot(Player player, Enemy attacker);

    //public abstract boolean attackDelay(float delay, float dt);
}
