package com.game.Behaviors.Attacks;

import com.game.Entities.Enemies.Enemy;
import com.game.Entities.Player;
import com.game.Entities.Projectile;

//attack when enemy comes in contact with player.
public class Contact extends Attack
{

    public Contact(){}

    @Override
    public void attack(Player player, Enemy attacker) {
        if(attacker.sprite.getBoundingRectangle().overlaps(player.sprite.getBoundingRectangle())) {
            player.gotHit(attacker.damageValue);
        }
    }

    @Override
    public Projectile shoot(Player player, Enemy attacker) {
        return null;
    }
}
