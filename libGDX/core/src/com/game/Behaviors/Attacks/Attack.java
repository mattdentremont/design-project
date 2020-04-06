package com.game.Behaviors.Attacks;

import com.game.Entities.Enemies.Enemy;
import com.game.Entities.Player;
import com.game.Entities.Projectile;

//Abstract class that allows for different attacking methods that enemies or players can use.
public abstract class Attack {

    public Attack(){
    }

    public abstract void attack(Player player, Enemy attacker);

    //For The Un-Implemented Projectiles
    //public abstract Projectile shoot(Player player, Enemy attacker);

}
