package sg.binner.adminplugin;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerTeleportEvent;

public class EnderListener implements Listener {
    private AdminMain plugin;

    public EnderListener(AdminMain plugin){
        this.plugin = plugin;
        Bukkit.getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void onEnderPearl(PlayerTeleportEvent event){
        Player p = event.getPlayer();
        if(event.getCause() == PlayerTeleportEvent.TeleportCause.ENDER_PEARL) {
            if (p.hasPermission("adminplugin.endermastery")) {
                event.setCancelled(true);
                p.teleport(event.getTo());
            }
        }
    }
}
