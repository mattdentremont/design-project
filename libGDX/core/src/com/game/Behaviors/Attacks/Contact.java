package com.game.Behaviors.Attacks;

import com.game.Entities.Enemies.Enemy;
import com.game.Entities.Player;
import com.game.Entities.Projectile;

public class Contact extends Attack
{

    public Contact(){}

    @Override
    //Checks if sprites overlap
    public void attack(Player player, Enemy attacker) {
        if(attacker.sprite.getBoundingRectangle().overlaps(player.sprite.getBoundingRectangle())) {
            player.gotHit(attacker.damageValue);
        }
    }

}
