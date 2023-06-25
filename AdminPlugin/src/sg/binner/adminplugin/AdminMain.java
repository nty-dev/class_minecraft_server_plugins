package sg.binner.adminplugin;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.plugin.java.JavaPlugin;
import net.milkbowl.vault.permission.Permission;
import org.bukkit.plugin.RegisteredServiceProvider;

public class AdminMain extends JavaPlugin{
    private static Permission perms = null;

    private boolean setupPermissions() {
        RegisteredServiceProvider<Permission> rsp = getServer().getServicesManager().getRegistration(Permission.class);
        perms = rsp.getProvider();
        return perms != null;
    };

    private void dispenserRecipe() {
        ItemStack dispenser = new ItemStack(Material.DISPENSER, 1);
        NamespacedKey key = new NamespacedKey(this, "dispenser");
        ShapedRecipe dispenseRecipe = new ShapedRecipe(key, dispenser);
        dispenseRecipe.shape("AB ", "ACB","AB ");
        dispenseRecipe.setIngredient('B', Material.STICK);
        dispenseRecipe.setIngredient('A', Material.STRING);
        dispenseRecipe.setIngredient('C', Material.DROPPER);
        Bukkit.addRecipe(dispenseRecipe);
    };
    private void dispenserRecipe2() {
        ItemStack dispenser = new ItemStack(Material.DISPENSER, 1);
        NamespacedKey key = new NamespacedKey(this, "dispenser2");
        ShapedRecipe dispenseRecipe = new ShapedRecipe(key, dispenser);
        dispenseRecipe.shape(" BA", "BCA"," BA");
        dispenseRecipe.setIngredient('B', Material.STICK);
        dispenseRecipe.setIngredient('A', Material.STRING);
        dispenseRecipe.setIngredient('C', Material.DROPPER);
        Bukkit.addRecipe(dispenseRecipe);
    };

    @Override
    public void onEnable(){
        getLogger().info("Admin Plugin Started.");
        setupPermissions();
        saveDefaultConfig();
        getCommand("admin").setExecutor(new AdminCmd(this));
        getCommand("sethealth").setExecutor(new SetHealth(this));
        new EnderListener(this);
        dispenserRecipe();
        dispenserRecipe2();
    }

    @Override
    public void onDisable(){
        getLogger().info("Admin Plugin Stopped.");
    }

    public static Permission getPermissions() {
        return perms;
    }
}

