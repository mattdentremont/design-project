package com.game.Behaviors;

import com.game.Entities.Enemy;
import com.game.Entities.Player;
import com.game.Entities.Projectile;

public class Shoot extends Attack {

    @Override
    public void attack(Player player, Enemy attacker) {
        //TODO:create projectiles at enemy position towards player's current position
    }
    public Projectile shoot(Player player, Enemy attacker) {
        //TODO:create projectiles at enemy position towards player's current position
        Projectile bullet = new Projectile(player,attacker.getPosX()+attacker.sprite.getWidth()/2,attacker.getPosY()+attacker.sprite.getHeight()/2);

        return bullet;
    }

}
