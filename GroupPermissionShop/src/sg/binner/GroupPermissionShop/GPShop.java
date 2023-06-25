package sg.binner.GroupPermissionShop;

import org.black_ixx.bossshop.api.BossShopAddon;
import org.bukkit.command.CommandSender;

public class GPShop extends BossShopAddon{

    @Override
    public String getAddonName() {
        return "GPShop"; // Here you put the name you want the Addon to have
    }
    @Override
    public String getRequiredBossShopVersion() {
        return "1.9.5"; // Here you put in the name of the oldest BSP build that supports your Addon (only numbers and dots; without "v" prefix)
    }

    @Override
    public void enableAddon() {
        getLogger().info("LOADED!");
        new GPListener(this);
    }

    @Override
    public void disableAddon() {
        getLogger().info("UNLOADED!");
    }

    @Override
    public void bossShopFinishedLoading() {
        // This method is executed when BossShopPro is completely loaded (Including shops, shopitems, rewardtypes etc.)
    }

    @Override
    public void bossShopReloaded(CommandSender sender) {
        // This method is executed when BossShopPro is reloaded manually ("/bossshop reload")
    }


}