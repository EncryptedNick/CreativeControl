package net.xornick.creativecontrol.listeners;

import net.xornick.creativecontrol.CreativeControl;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.block.Block;
import org.bukkit.block.Chest;
import org.bukkit.block.DoubleChest;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerPickupItemEvent;

import java.util.List;

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

    @EventHandler
    public void onCreativePlayerDropItem(PlayerDropItemEvent event) {
        if (!plugin.getConfig().getBoolean("block.dropItems", true)) {
            return;
        }
        Player player = event.getPlayer();

        if (player.getGameMode() != GameMode.CREATIVE) {
            return;
        }
        if (player.hasPermission("creativecontrol.admin")) {
            return;
        }
        player.sendMessage(ChatColor.RED + "You are not permitted to do this. Are you in the right mode?");
        event.setCancelled(true);
    }

    @EventHandler
    public void onCreativeBlockPlace(BlockPlaceEvent event) {
        if (!plugin.getConfig().getBoolean("block-place-blacklist.enabled", true)) {
            return;
        }
        Player player = event.getPlayer();

        if (player.getGameMode() != GameMode.CREATIVE) {
            return;
        }
        if (player.hasPermission("creativecontrol.admin")) {
            return;
        }
        List<Integer> blacklistedBlocks = plugin.getConfig().getIntegerList("block-place-blacklist.blacklist");
        Block block = event.getBlock();

        if (!blacklistedBlocks.contains(block.getTypeId())) {
            return;
        }
        player.sendMessage(ChatColor.RED + "You are not permitted to do this. Are you in the right mode?");
        event.setCancelled(true);
    }

    @EventHandler
    public void onCreativeChestOpen(InventoryOpenEvent event) {
        if (!plugin.getConfig().getBoolean("block.chests", true)) {
            return;
        }
        if (! (event.getInventory().getHolder() instanceof Chest) && ! (event.getInventory().getHolder() instanceof DoubleChest)) {
            return;
        }
        Player player = (Player) event.getPlayer();

        if (player.getGameMode() != GameMode.CREATIVE) {
            return;
        }
        if (player.hasPermission("creativecontrol.admin")) {
            return;
        }
        player.sendMessage(ChatColor.RED + "You are not permitted to do this. Are you in the right mode?");
        event.setCancelled(true);
    }

    @EventHandler
    public void onCreativePlayerPickupItem(PlayerPickupItemEvent event) {
        if (!plugin.getConfig().getBoolean("block.itemPickup", true)) {
            return;
        }
        Player player = event.getPlayer();

        if (player.getGameMode() != GameMode.CREATIVE) {
            return;
        }
        if (player.hasPermission("creativecontrol.admin")) {
            return;
        }
        // We're not going to send the player a message, that'd be a bit spammy if they're in events like huge drop parties.
        event.setCancelled(true);
    }

    @EventHandler
    public void onCreativeBlockBreak(BlockBreakEvent event) {
        if (!plugin.getConfig().getBoolean("block-break-blacklist", true)) {
            return;
        }
        Player player = event.getPlayer();

        if (player.getGameMode() != GameMode.CREATIVE) {
            return;
        }
        if (player.hasPermission("creativecontrol.admin")) {
            return;
        }
        List<Integer> blacklistedBlocks = plugin.getConfig().getIntegerList("block-break-blacklist.blacklisted");
        Block block = event.getBlock();

        if (!blacklistedBlocks.contains(block.getTypeId())) {
            return;
        }
        player.sendMessage(ChatColor.RED + "You are not permitted to do this. Are you in the right mode?");
        event.setCancelled(true);
    }

    @EventHandler // Block creature spawning (with monster eggs)
    public void onCreatureSpawn(CreatureSpawnEvent event) {
        if (!plugin.getConfig().getBoolean("block.spawnEggs")) {
            return;
        }
        if (event.getSpawnReason() != CreatureSpawnEvent.SpawnReason.SPAWNER_EGG) {
            return;
        }
        event.setCancelled(true);
    }
}
