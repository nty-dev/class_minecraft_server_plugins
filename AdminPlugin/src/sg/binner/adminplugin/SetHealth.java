package sg.binner.adminplugin;

import net.milkbowl.vault.chat.Chat;
import net.milkbowl.vault.permission.Permission;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.attribute.Attribute;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

public class SetHealth implements CommandExecutor {
    private AdminMain plugin;

    public SetHealth(AdminMain plugin){
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            if (sender.hasPermission("adminplugin.sethealth")) {
                Player p = (Player) sender;
                if (args.length == 0){
                    p.sendMessage(ChatColor.RED + "Correct usage: /sethealth [Health] [Username]");
                    return false;
                }
                try {
                    double healthvalue = Integer.parseInt(args[0]);
                    if (healthvalue < 1){
                        p.sendMessage(ChatColor.RED + "Health must be larger than 0!");
                        return false;
                    }
                    Player target;
                    if (args.length == 1){
                        target = (Player) sender;
                    } else {
                        target = Bukkit.getPlayerExact(args[1]);
                    }
                    if (target == null) {
                        p.sendMessage(ChatColor.RED + "Target player " + args[1] + " is not online!");
                        return false;
                    } else {
                        target.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(healthvalue);
                    }
                } catch(NumberFormatException nfe){
                    p.sendMessage(ChatColor.RED + args[0] + " is not an integer value!");
                    return false;
                }

            } else {
                Player p = (Player) sender;
                p.sendMessage(ChatColor.RED + "You do not have the permissions to set people's health!");
            }
        } else if (sender instanceof ConsoleCommandSender) {
            if (args.length == 0){
                return false;
            }
            try {
                double healthvalue = Integer.parseInt(args[0]);
                if (healthvalue < 1){
                    return false;
                }
                Player target  = Bukkit.getPlayerExact(args[1]);
                if (target == null) {
                    return false;
                } else {
                    target.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(healthvalue);
                }
            } catch(NumberFormatException nfe){
                return false;
            }
        }
        return true;
    }
}
