package net.xornick.creativecontrol;

import net.xornick.creativecontrol.commands.CommandReload;
import net.xornick.creativecontrol.inventories.InventoryManager;
import net.xornick.creativecontrol.listeners.PlayerListener;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public final class CreativeControl extends JavaPlugin {

    private static CreativeControl instance;

    @Override
    public void onEnable() {
        instance = this;
        registerListeners();
        getCommand("ccreload").setExecutor(new CommandReload());
    }

    private void registerListeners() {
        PluginManager pm = getServer().getPluginManager();
        pm.registerEvents(new PlayerListener(), this);
        pm.registerEvents(new InventoryManager(), this);
    }

    public static CreativeControl getInstance() {
        return instance;
    }
}
