package org.tfsmp.smpvisitor.command;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import org.tfsmp.smpvisitor.gui.VisitorLocationsGUI;

public class Command_visit implements CommandExecutor
{
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String c, String[] args)
    {
        if (args.length > 1)
        {
            return false;
        }
        if (args.length == 1)
        {
            if (!(sender instanceof ConsoleCommandSender) && !sender.isOp())
            {
                sender.sendMessage(ChatColor.RED + "No permission.");
                return true;
            }
            Player player = Bukkit.getPlayer(args[0]);
            if (player == null)
            {
                sender.sendMessage(ChatColor.GRAY + "Player not found!");
                return true;
            }
            new VisitorLocationsGUI().open(player);
        }
        if (sender instanceof ConsoleCommandSender)
        {
            sender.sendMessage(ChatColor.RED + "Console cannot use this command.");
            return true;
        }
        Player player = (Player) sender;
        new VisitorLocationsGUI().open(player);
        return true;
    }
}