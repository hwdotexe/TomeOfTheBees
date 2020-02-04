package tech.hadenw.tomeofthebees.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import tech.hadenw.tomeofthebees.BeePermissions;
import tech.hadenw.tomeofthebees.ItemFactory;
import tech.hadenw.tomeofthebees.TOTB;
import tech.hadenw.tomeofthebees.storage.EffectType;

public class BeesCommand implements CommandExecutor {
    private TOTB plugin;

    public BeesCommand(TOTB c){
        plugin=c;
    }

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if(sender.hasPermission(BeePermissions.RUN_COMMAND.getNode())){
            if(args.length == 0){
                sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "" +
                        "&e=====[ &6&lBEES &e]=====\n" +
                        "&f/bees getJar\n" +
                        "/bees addStatus <player> <status>\n" +
                        "&e=================="));
            }else if(args.length == 1 && args[0].equalsIgnoreCase("getJar")){
                if(sender instanceof Player) {
                    ((Player) sender).getInventory().addItem(ItemFactory.getJarOfBees());
                    sender.sendMessage("Please accept this small jar of bees.");
                } else {
                    sender.sendMessage("Sorry mate, you gotta have hands to throw a JAR OF BEEEES!");
                }
            }else if(args.length == 3 && args[0].equalsIgnoreCase("addStatus")){
                Player op = Bukkit.getPlayer(args[1]);

                if(op != null){
                    if(EffectType.containsValue(args[2])) {
                        EffectType t = EffectType.valueOf(args[2].toUpperCase());

                        plugin.getStorage().getPlayerInfo(op.getUniqueId().toString()).addEffect(t);
                        sender.sendMessage("It is done m'lord");
                    }else{
                        sender.sendMessage("That status doesn't exist!");
                    }
                }else{
                    sender.sendMessage("Player not found :[");
                }
            }else{
                sender.sendMessage("Incorrect usage of that command, there, sir.");
            }
            return true;
        }
        return false;
    }
}
