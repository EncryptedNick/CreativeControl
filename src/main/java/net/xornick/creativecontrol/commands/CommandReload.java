package net.xornick.creativecontrol.commands;

import net.xornick.creativecontrol.CreativeControl;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandReload implements CommandExecutor {


    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender.hasPermission("creativecontrol.admin")) {
            CreativeControl.getInstance().reloadConfig();
            if (sender instanceof Player) {
                Player player = (Player) sender;
                player.sendMessage(ChatColor.GREEN + "Configuration reloaded!");
                return true;
            } else {
                sender.sendMessage(ChatColor.GREEN + "Hey! We reloaded the configuration for you.");
                return true;
            }
        }
        return false;
    }
}
