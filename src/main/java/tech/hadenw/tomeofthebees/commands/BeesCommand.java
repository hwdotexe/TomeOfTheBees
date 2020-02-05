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
                        "/bees clearStatuses <player>\n" +
                        "/bees listStatuses" +
                        "&e=================="));
            }else if(args.length == 1 && args[0].equalsIgnoreCase("getJar")){
                if(sender instanceof Player) {
                    ((Player) sender).getInventory().addItem(ItemFactory.getJarOfBees());
                    sender.sendMessage("Please accept this small jar of bees.");
                } else {
                    sender.sendMessage("Sorry mate, you gotta have hands to throw a JAR OF BEEEES!");
                }
            }else if(args.length == 1 && args[0].equalsIgnoreCase("listStatuses")) {
                StringBuilder sb = new StringBuilder();

                for(EffectType et : EffectType.values()){
                    if(sb.length() > 0){
                        sb.append(", ");
                    }

                    sb.append(et.name());
                }

                sender.sendMessage("Valid effect types are: "+sb.toString());
            }else if(args.length == 2 && args[0].equalsIgnoreCase("clearStatuses")) {
                Player op = Bukkit.getPlayer(args[1]);

                if(op != null){
                    plugin.getStorage().getPlayerInfo(op.getUniqueId().toString()).removeEffects();
                    sender.sendMessage("The player has been cured.");
                }else{
                    sender.sendMessage("Player not found :[");
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
