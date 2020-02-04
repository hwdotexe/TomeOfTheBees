package tech.hadenw.tomeofthebees;

import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.PotionMeta;
import org.bukkit.potion.PotionData;

public class ItemFactory {
    public static ItemStack getJarOfBees(){
        ItemStack jar = new ItemStack(Material.POTION);
        ItemMeta im = jar.getItemMeta();

        im.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&6Jar of Bees"));
        jar.setItemMeta(im);

        PotionMeta pm = (PotionMeta)jar.getItemMeta();

        pm.setColor(Color.YELLOW);
        jar.setItemMeta(pm);

        return jar;
    }
}
