package sg.binner.GroupPermissionShop;

import org.black_ixx.bossshop.events.BSRegisterTypesEvent;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import static org.bukkit.Bukkit.getServer;

public class GPListener implements Listener {
    private GPShop plugin;

    public GPListener(GPShop plugin){
        this.plugin = plugin;
        Bukkit.getPluginManager().registerEvents(this, plugin);
    };

    @EventHandler
    public void registerEvents(BSRegisterTypesEvent event) {
        BSRewardTypeGroupPermission rewardtype = new BSRewardTypeGroupPermission();
        BSRewardTypeExtraHearts extrahearttype = new BSRewardTypeExtraHearts();
        extrahearttype.register();
        rewardtype.register();
    };

}
