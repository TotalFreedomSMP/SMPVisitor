package org.tfsmp.smpvisitor.gui;

import lombok.Getter;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.tfsmp.smpvisitor.SMPVisitor;
import org.tfsmp.smpvisitor.util.SUtil;
import org.tfsmp.smpvisitor.visiting.VisitorLocation;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class VisitorLocationsGUI extends GUI
{
    private static SMPVisitor plugin = SMPVisitor.getPlugin();

    private static final List<Integer> border = Arrays.asList(0, 2, 3, 4, 5, 6, 7, 8, 9, 18, 27, 36, 17, 26, 35, 44, 45, 46, 47, 48, 50, 51, 52, 53);
    private static final List<Integer> listing = Arrays.asList(10, 11, 12, 13, 14, 15, 16, 19, 20, 21, 22, 23, 24, 25, 28, 29, 30, 31, 32, 33, 34, 37, 38, 39, 40, 41, 42, 43);
    private static final ItemStack bitem = SUtil.createNamedItem(Material.GRAY_STAINED_GLASS_PANE, ChatColor.DARK_GRAY + "");

    private final List<VisitorLocation> locations = new ArrayList<>();

    @Getter
    private final int page;

    public VisitorLocationsGUI(int page)
    {
        super("Visitor Locations", 54);
        this.page = page;
        for (String identifier : plugin.locations.getKeys(false))
        {
            locations.add(VisitorLocation.getLocation(identifier));
        }
        super.setSlot(1, SUtil.createNamedItem(Material.PAPER, ChatColor.GREEN + "Page " + page));
        for (int b : border)
        {
            super.setSlot(b, bitem);
        }
        for (int i = 0; i < listing.size(); i++)
        {
            VisitorLocation vl;
            try
            {
                vl = locations.get(((page - 1) * 27) + i);
            }
            catch (IndexOutOfBoundsException ex)
            {
                continue;
            }
            if (vl == null)
                continue;
            super.setSlot(listing.get(i), vl.stack());
        }
        if (page == 1)
            super.setSlot(46, bitem);
        else
            super.setSlot(46, SUtil.createNamedItem(Material.ARROW, ChatColor.AQUA + "Previous Page"));
        super.setSlot(49, SUtil.createNamedItem(Material.ARROW, ChatColor.AQUA + "Exit"));
        try
        {
            locations.get(page * 27);
        }
        catch (IndexOutOfBoundsException ex)
        {
            super.setSlot(52, bitem);
            return;
        }
        super.setSlot(52, SUtil.createNamedItem(Material.ARROW, ChatColor.AQUA + "Next Page"));
    }

    public VisitorLocationsGUI()
    {
        this(1);
    }
}