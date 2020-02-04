package tech.hadenw.tomeofthebees.listeners;

import org.bukkit.entity.Bee;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityTargetLivingEntityEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import tech.hadenw.tomeofthebees.TOTB;
import tech.hadenw.tomeofthebees.storage.EffectType;

import java.util.Collections;
import java.util.List;

public class BeeListener implements Listener {
    private TOTB plugin;

    public BeeListener(TOTB c){
        plugin = c;
    }

    @EventHandler
    public void onBeeTargetPlayer(EntityTargetLivingEntityEvent e){
        if(e.getEntity() instanceof Bee && e.getTarget() instanceof Player) {
            Player p = (Player) e.getTarget();

            if (plugin.getStorage().getPlayerInfo(p.getUniqueId().toString()).hasEffect(EffectType.QUEEN_BEE)) {
                e.setCancelled(true);
            }
        }
    }

    @EventHandler
    public void onBeeDamagePlayer(EntityDamageByEntityEvent e){
        if(e.getDamager() instanceof Bee && e.getEntity() instanceof Player) {
            Player p = (Player) e.getEntity();

            if (plugin.getStorage().getPlayerInfo(p.getUniqueId().toString()).hasEffect(EffectType.ALLERGY)) {
                e.setDamage(e.getDamage() * 2);
            }
        }
    }

    @EventHandler
    public void onPlayerMoveNearBees(PlayerMoveEvent e){
        Player p = e.getPlayer();

        if (plugin.getStorage().getPlayerInfo(p.getUniqueId().toString()).hasEffect(EffectType.HONEY_SMELL)) {
            for(Entity ent : p.getWorld().getNearbyEntities(p.getLocation(), 25, 25, 25)){
                if(ent instanceof Bee){
                    Bee bee = (Bee)ent;

                    if(bee.getTarget() != p){
                        bee.setTarget(p);
                    }
                }
            }
        }
    }
}
