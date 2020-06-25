package org.tfsmp.smpvisitor.gui;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.tfsmp.smpvisitor.SMPVisitor;
import org.tfsmp.smpvisitor.util.SUtil;
import org.tfsmp.smpvisitor.visiting.VisitorLocation;

import java.util.Arrays;
import java.util.List;

public class ConfirmTravelGUI extends GUI
{
    private static SMPVisitor plugin = SMPVisitor.getPlugin();

    private static final List<Integer> blank = Arrays.asList(3, 5, 12, 14, 21, 23);
    public static final List<Integer> confirm = Arrays.asList(0, 1, 2, 9, 10, 11, 18, 19, 20);
    public static final List<Integer> cancel = Arrays.asList(6, 7, 8, 15, 16, 17, 24, 25, 26);
    private static final ItemStack bitem = SUtil.createNamedItem(Material.GRAY_STAINED_GLASS_PANE, ChatColor.DARK_GRAY + "");
    private static final ItemStack bconfirm = SUtil.createNamedItem(Material.LIME_STAINED_GLASS_PANE, ChatColor.GREEN + "CONFIRM");
    private static final ItemStack bcancel = SUtil.createNamedItem(Material.RED_STAINED_GLASS_PANE, ChatColor.RED + "CANCEL");

    public ConfirmTravelGUI(Player player, VisitorLocation vl)
    {
        super("Confirm", 27);
        for (int b : blank)
        {
            super.setSlot(b, bitem);
        }
        for (int c : confirm)
        {
            super.setSlot(c, bconfirm);
        }
        for (int c : cancel)
        {
            super.setSlot(c, bcancel);
        }
        Location loc = player.getLocation();
        super.setSlot(4, SUtil.getGlobeHead(ChatColor.GREEN + "" + (int) loc.getX() + ", " + (int) loc.getY() + ", " + (int) loc.getZ(), loc.getWorld()));
        super.setSlot(13, SUtil.createNamedItem(Material.ARROW, ChatColor.GOLD + "↓"));
        super.setSlot(22, vl.stack());
    }
}