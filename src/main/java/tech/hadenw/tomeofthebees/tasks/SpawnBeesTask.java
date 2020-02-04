package tech.hadenw.tomeofthebees.tasks;

import org.bukkit.Location;
import org.bukkit.entity.EntityType;
import org.bukkit.scheduler.BukkitRunnable;

public class SpawnBeesTask extends BukkitRunnable {
    private Location l;
    private int iterations;

    public SpawnBeesTask(Location loc){
        l = loc;
        iterations = 7;
    }

    public void run(){
        if(iterations >= 0){
            iterations--;

            l.getWorld().spawnEntity(l, EntityType.BEE);
            l.getWorld().spawnEntity(l, EntityType.BEE);
        }else{
            this.cancel();
        }
    }
}
