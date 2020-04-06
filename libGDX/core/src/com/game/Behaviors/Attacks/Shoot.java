package com.game.Behaviors.Attacks;

import com.game.Entities.Enemies.Enemy;
import com.game.Entities.Player;
import com.game.Entities.Projectile;


//NOT IMPLEMENTED: We didn't implement projectiles. This class would allow enemies to shoot projectiles towards the player.
//We did not originally plan to implement projectiles, we just tried to do them as extra work but didn't finish them.
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
