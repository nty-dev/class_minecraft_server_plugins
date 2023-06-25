package sg.binner.GroupPermissionShop;

import org.black_ixx.bossshop.core.BSBuy;
import org.black_ixx.bossshop.core.rewards.BSRewardType;
import org.black_ixx.bossshop.managers.ClassManager;
import org.black_ixx.bossshop.managers.misc.InputReader;
import org.black_ixx.bossshop.managers.misc.StringManipulationLib;
import org.bukkit.entity.Player;
import org.bukkit.Bukkit;
import org.bukkit.event.inventory.ClickType;
import java.util.List;

import net.milkbowl.vault.permission.Permission;

public class BSRewardTypeGroupPermission extends BSRewardType {

    public Object createObject(Object o, boolean force_final_state) { // force_final_state will always be true except being accessed by the BossShopProConfigure too
        return InputReader.readStringList(o); // Here you can transform the object given in the config file into whatever you want. The object returned here will be used later.
    }

    public boolean validityCheck(String item_name, Object o) { // This method is executed after "createObject" has been called. Here you can check if the object is valid.
        if (o!=null) {
            return true;
        }
        ClassManager.manager.getBugFinder().severe("Was not able to create ShopItem "+item_name+"!");
        return false;
    }

    public void enableType() { // If your RewardTypes needs any additional libraries or features enabled, you can do that here
        ClassManager.manager.getSettings().setPermissionsEnabled(true);
        ClassManager.manager.getSettings().setVaultEnabled(true);
    }

    @Override
    public boolean canBuy(Player p, BSBuy buy, boolean message_if_no_success, Object reward, ClickType clickType) { // Here you can allow/deny the player to purchase the item
        Permission per = ClassManager.manager.getVaultHandler().getPermission();
        List<String> groupperms = (List<String>) reward;
        for (String s : groupperms){
            if (!per.playerInGroup(p, s)){
                return true;
            }
        }
        return false;
    }

    @Override
    public void giveReward(Player p, BSBuy buy, Object reward, ClickType clickType) { // If everything went successful the player is rewarded here
        List<String> groupperms = (List<String>) reward;

        Permission per = ClassManager.manager.getVaultHandler().getPermission();
        for (String s : groupperms) {
            per.playerAddGroup(null, p, s);
        }
    }

    @Override
    public String getDisplayReward(Player p, BSBuy buy, Object reward, ClickType clickType) { // Here you define the way the "%reward%" placeholder will display this kind of reward
        List<String> permissions = (List<String>) reward;
        String permissions_formatted = StringManipulationLib.formatList(permissions);
        return ClassManager.manager.getMessageHandler().get("Display.GroupPermission").replace("%group%", permissions_formatted);
    }

    @Override
    public String[] createNames() { // Here you define all names that will lead to your reward, with the first entry being the official main name.
        return new String[]{"grouppermission", "grouppermissions"};
    }

    @Override
    public boolean mightNeedShopUpdate() { // Here you can define if the shop should be updated after this reward is purchased
        return true;
    }

    // Following methods are optional:

    @Override
    public boolean logTransaction() {
        return true; // True by default. If you don't want transactions related to this reward to be logged, set this to false
    }


}