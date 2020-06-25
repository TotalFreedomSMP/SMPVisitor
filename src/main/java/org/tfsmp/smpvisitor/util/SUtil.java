package org.tfsmp.smpvisitor.util;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

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

    public static String color(String s)
    {
        return ChatColor.translateAlternateColorCodes('&', s);
    }
}