package net.xornick.creativecontrol.inventories;

import net.xornick.creativecontrol.CreativeControl;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerGameModeChangeEvent;


public class InventoryManager implements Listener {

    private CreativeControl plugin;

    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
    public void onPlayerGameModeChange(PlayerGameModeChangeEvent event) {
        if (!plugin.getConfig().getBoolean("inventoryStorage.enabled", true)) {
            return;
        }
        Player player = event.getPlayer();

    }
}
