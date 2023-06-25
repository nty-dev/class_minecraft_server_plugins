package sg.binner.insuranceplugin;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.entity.Player;
import net.milkbowl.vault.permission.Permission;

public class InsuranceListener implements Listener {
    private InsuranceMain plugin;

    public InsuranceListener(InsuranceMain plugin){
        this.plugin = plugin;
        Bukkit.getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void onDeath(PlayerDeathEvent event){
        String deathmsg = event.getDeathMessage();
        event.setDeathMessage("");
        Player p = event.getEntity();
        Permission per = plugin.getPermissions();
        String insurancegroup = plugin.getConfig().getString("insurancegroup");
        if (per.playerInGroup(p, insurancegroup)){
            Bukkit.broadcastMessage(ChatColor.DARK_GREEN + deathmsg);
            p.sendMessage(ChatColor.AQUA + "Insurance has been activated, saving all your items and xp!");
            p.sendMessage(ChatColor.YELLOW + "Purchase insurance in /shop to safeguard your next death!");
            per.playerRemoveGroup(null, p, insurancegroup);
        } else {
            Bukkit.broadcastMessage(deathmsg);
            p.sendMessage(ChatColor.RED + "Unfortunately, since you did not buy insurance, you have lost all your items and xp on death. Purchase insurance in /shop next time!");
        }

    }
}
