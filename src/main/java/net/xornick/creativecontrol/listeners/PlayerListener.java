package net.xornick.creativecontrol.listeners;

import net.xornick.creativecontrol.CreativeControl;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

public class PlayerListener implements Listener {

    private static CreativeControl plugin = CreativeControl.getInstance();

    @EventHandler
    public void onPlayerDamageByCreativePlayer(EntityDamageByEntityEvent event) {
        if (!plugin.getConfig().getBoolean("block.pvp", true)) {
            return;
        }
        if (event.getDamager().getType() != EntityType.PLAYER || event.getEntity().getType() != EntityType.PLAYER) {
            return;
        }
        Player cPlayer = (Player) event.getDamager();

        if (cPlayer.getGameMode() != GameMode.CREATIVE) {
            return;
        }
        if (cPlayer.hasPermission("creativecontrol.admin")) {
            return;
        }
        cPlayer.sendMessage(ChatColor.RED + "You are not permitted to do this. Are you in the right mode?");
        event.setCancelled(true);
    }
}
