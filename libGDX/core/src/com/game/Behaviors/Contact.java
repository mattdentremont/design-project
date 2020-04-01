package com.game.Behaviors;

import com.game.Entities.Enemy;
import com.game.Entities.Player;

public class Contact extends Attack
{

    public Contact(){}

    @Override
    public void attack(Player player, Enemy attacker) {
        if(attacker.sprite.getBoundingRectangle().overlaps(player.sprite.getBoundingRectangle())) {
            player.gotHit(attacker.damageValue);
        }
    }
}
