package org.tfsmp.smpvisitor.util;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

public class SUtil
{
    public static ItemStack createNamedItem(Material material, String name)
    {
        ItemStack stack = new ItemStack(material);
        ItemMeta meta = stack.getItemMeta();
        meta.setDisplayName(name);
        stack.setItemMeta(meta);
        return stack;
    }

    public static int randomInteger(int min, int max)
    {
        int range = max - min + 1;
        int value = (int)(Math.random() * range) + min;
        return value;
    }

    public static ItemStack getGlobeHead(String name, World world)
    {
        ItemStack stack = createNamedItem(Material.PLAYER_HEAD, name);
        SkullMeta meta = (SkullMeta) stack.getItemMeta();
        switch (world.getEnvironment())
        {
            case NORMAL: meta.setOwningPlayer(Bukkit.getOfflinePlayer("Dipicrylamine")); break;
            case NETHER: meta.setOwningPlayer(Bukkit.getOfflinePlayer("r04dk1ll")); break;
            case THE_END: meta.setOwningPlayer(Bukkit.getOfflinePlayer("80000")); break;
        }
        stack.setItemMeta(meta);
        return stack;
    }

    public static String color(String s)
    {
        return ChatColor.translateAlternateColorCodes('&', s);
    }
}