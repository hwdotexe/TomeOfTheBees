package tech.hadenw.tomeofthebees.listeners;

import org.bukkit.Material;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Bee;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityTargetLivingEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import tech.hadenw.tomeofthebees.ItemFactory;
import tech.hadenw.tomeofthebees.TOTB;
import tech.hadenw.tomeofthebees.storage.EffectType;
import tech.hadenw.tomeofthebees.tasks.SpawnBeesTask;

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
            Bee bee = (Bee)e.getEntity();

            if (plugin.getStorage().getPlayerInfo(p.getUniqueId().toString()).hasEffect(EffectType.QUEEN_BEE)) {
                e.setCancelled(true);
                bee.setAnger(0);
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
                        bee.setAnger(1200);
                    }
                }
            }
        }
    }

    @EventHandler
    public void onPlayerUseJarOfBees(PlayerInteractEvent e){
        if(e.getAction() == Action.RIGHT_CLICK_BLOCK){
            if(e.getPlayer().getInventory().getItemInMainHand().isSimilar(ItemFactory.getJarOfBees())){
                if(e.getClickedBlock().getRelative(BlockFace.UP).getType()== Material.AIR) {
                    e.getPlayer().getInventory().remove(e.getPlayer().getInventory().getItemInMainHand());

                    // Spawn bees at the location.
                    new SpawnBeesTask(e.getClickedBlock().getRelative(BlockFace.UP).getLocation()).runTaskTimer(plugin, 0, 10);
                }
            }
        }
    }
}
