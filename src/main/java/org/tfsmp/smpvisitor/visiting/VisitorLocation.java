package org.tfsmp.smpvisitor.visiting;

import lombok.Getter;
import lombok.Setter;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;
import org.tfsmp.smpvisitor.SMPVisitor;
import org.tfsmp.smpvisitor.util.SUtil;

import javax.xml.bind.Marshaller;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class VisitorLocation
{
    private static SMPVisitor plugin = SMPVisitor.getPlugin();

    @Getter
    private String identifier;

    @Getter
    private String name;

    @Getter
    private Location location;

    @Getter
    @Setter
    private String owner;

    public VisitorLocation(String identifier, String name, Location location, String owner)
    {
        this.identifier = identifier;
        this.name = name;
        this.location = location;
        this.owner = owner;
    }

    public void saveData()
    {
        plugin.locations.set(identifier.toLowerCase() + ".name", name);
        plugin.locations.set(identifier.toLowerCase() + ".location.world", location.getWorld().getName());
        plugin.locations.set(identifier.toLowerCase() + ".location.x", location.getX());
        plugin.locations.set(identifier.toLowerCase() + ".location.y", location.getY());
        plugin.locations.set(identifier.toLowerCase() + ".location.z", location.getZ());
        plugin.locations.set(identifier.toLowerCase() + ".owner", owner);
        plugin.locations.save();
    }

    public ItemStack stack()
    {
        ItemStack stack = new ItemStack(Material.PLAYER_HEAD);
        SkullMeta meta = (SkullMeta) stack.getItemMeta();
        meta.setOwningPlayer(Bukkit.getOfflinePlayer(owner));
        meta.setDisplayName(SUtil.color(name));
        List<String> lore = new ArrayList<>();
        lore.add(ChatColor.DARK_GRAY + identifier);
        meta.setLore(lore);
        stack.setItemMeta(meta);
        return stack;
    }

    public static VisitorLocation getLocation(String identifier)
    {
        if (!plugin.locations.contains(identifier))
        {
            return null;
        }
        return new VisitorLocation(identifier,
                plugin.locations.getString(identifier.toLowerCase() + ".name"),
                new Location(Bukkit.getWorld(plugin.locations.getString(identifier.toLowerCase() + ".location.world")),
                        plugin.locations.getDouble(identifier.toLowerCase() + ".location.x"),
                        plugin.locations.getDouble(identifier.toLowerCase() + ".location.y"),
                        plugin.locations.getDouble(identifier.toLowerCase() + ".location.z")),
                plugin.locations.getString(identifier.toLowerCase() + ".owner"));
    }

    public static VisitorLocation createLocation(String identifier, String name, Location location, String owner)
    {
        return new VisitorLocation(identifier, name, location, owner);
    }
}