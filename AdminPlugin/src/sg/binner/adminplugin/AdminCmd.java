package sg.binner.adminplugin;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import net.milkbowl.vault.permission.Permission;

public class AdminCmd implements CommandExecutor {
    private AdminMain plugin;

    public AdminCmd(AdminMain plugin){
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            if (sender.hasPermission("adminplugin.admin")) {
                Player p = (Player) sender;
                Permission per = plugin.getPermissions();
                String admingroup = plugin.getConfig().getString("admingroup");
                if (per.playerInGroup(p, admingroup)) {
                    per.playerRemoveGroup(null, p, admingroup);
                    p.sendMessage(ChatColor.DARK_RED + "Admin mode has been disabled. /admin to enable.");
                } else {
                    per.playerAddGroup(null, p, admingroup);
                    p.sendMessage(ChatColor.DARK_RED + "Admin mode has been enabled. /admin to disable.");
                }
            } else {
                Player p = (Player) sender;
                p.sendMessage(ChatColor.RED + "My friend, this command is only for the admin! What you doing buddy?");
            }
        }
        return true;
    }
}
