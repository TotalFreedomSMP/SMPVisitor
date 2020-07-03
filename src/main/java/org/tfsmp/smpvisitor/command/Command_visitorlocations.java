package org.tfsmp.smpvisitor.command;

import org.apache.commons.lang.StringUtils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.tfsmp.smpvisitor.SMPVisitor;
import org.tfsmp.smpvisitor.visiting.VisitorLocation;

public class Command_visitorlocations implements CommandExecutor
{
    private static SMPVisitor plugin = SMPVisitor.getPlugin();

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String c, String[] args)
    {
        if (!(sender instanceof ConsoleCommandSender) && !sender.isOp())
        {
            sender.sendMessage(ChatColor.RED + "No permission.");
            return true;
        }
        if (args.length == 1)
        {
            if (args[0].equalsIgnoreCase("clear"))
            {
                for (String key : plugin.locations.getKeys(false))
                {
                    plugin.locations.set(key, null);
                }
                sender.sendMessage(ChatColor.GRAY + "Removed all visitor locations.");
                return true;
            }
        }
        if (args.length == 2)
        {
            if (args[0].equalsIgnoreCase("remove"))
            {
                String identifier = args[1];
                if (!plugin.locations.getKeys(false).contains(identifier))
                {
                    sender.sendMessage(ChatColor.RED + "That visitor location doesn't exist!");
                    return true;
                }
                plugin.locations.set(identifier, null);
                plugin.locations.save();
                sender.sendMessage(ChatColor.GREEN + "Removed that visitor location!");
                return true;
            }
        }
        if (args.length >= 7)
        {
            if (args[0].equalsIgnoreCase("add"))
            {
                String identifier = args[1];
                String skullOwner = args[2];
                if (skullOwner.equalsIgnoreCase("none"))
                    skullOwner = "MHF_Question";
                World world = Bukkit.getWorld(args[3]);
                if (world == null)
                {
                    sender.sendMessage(ChatColor.GRAY + "Could not find that world.");
                    return true;
                }
                int x, y, z;
                try
                {
                    x = Integer.parseInt(args[4]);
                    z = Integer.parseInt(args[5]);
                }
                catch (NumberFormatException ex)
                {
                    sender.sendMessage(ChatColor.GRAY + "The coordinates specified were invalid.");
                    return true;
                }
                String name = StringUtils.join(args, " ", 6, args.length);
                if (plugin.locations.getKeys(false).contains(identifier))
                {
                    sender.sendMessage(ChatColor.RED + "A visitor location with that identifier already exists!");
                    return true;
                }
                VisitorLocation vl = VisitorLocation.createLocation(identifier, name, new Location(world, x, world.getHighestBlockYAt(x, z), z), skullOwner);
                vl.saveData();
                sender.sendMessage(ChatColor.GREEN + "Created a new visitor location!");
                return true;
            }
        }
        return false;
    }
}
// /setblock ~ ~1 ~ minecraft:player_head[rotation=0]{Owner:{Id:"937fb91e-562d-4ab3-b495-3c8b183c38bb",Properties:{textures:[{Value:"eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYmFkYzA0OGE3Y2U3OGY3ZGFkNzJhMDdkYTI3ZDg1YzA5MTY4ODFlNTUyMmVlZWQxZTNkYWYyMTdhMzhjMWEifX19"}]}}} replace
// /vls add <identifier> <skullowner> <world> <x> <y> <z> <name...>