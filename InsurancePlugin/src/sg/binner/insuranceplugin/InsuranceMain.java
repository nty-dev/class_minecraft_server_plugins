package sg.binner.insuranceplugin;

import org.bukkit.plugin.java.JavaPlugin;
import net.milkbowl.vault.permission.Permission;
import org.bukkit.plugin.RegisteredServiceProvider;

public class InsuranceMain extends JavaPlugin{

    private static Permission perms = null;

    private boolean setupPermissions() {
        RegisteredServiceProvider<Permission> rsp = getServer().getServicesManager().getRegistration(Permission.class);
        perms = rsp.getProvider();
        return perms != null;
    };

    @Override
    public void onEnable(){
        getLogger().info("Insurance Plugin Started.");
        setupPermissions();
        saveDefaultConfig();
        new InsuranceListener(this);
    }

    @Override
    public void onDisable(){
        getLogger().info("Insurance Plugin Stopped.");
    }

    public static Permission getPermissions() {
        return perms;
    }
}
