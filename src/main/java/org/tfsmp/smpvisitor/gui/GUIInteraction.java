package org.tfsmp.smpvisitor.gui;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.tfsmp.smpvisitor.SMPVisitor;
import org.tfsmp.smpvisitor.util.SUtil;
import org.tfsmp.smpvisitor.visiting.VisitorLocation;

public class GUIInteraction implements Listener
{
    private SMPVisitor plugin;
    public GUIInteraction()
    {
        plugin = SMPVisitor.getPlugin();
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent e)
    {
        String title = e.getView().getTitle();
        if (title.equals("Visitor Locations"))
        {
            handleVisitorLocations(e);
        }
        if (title.equals("Confirm"))
        {
            handleConfirmTravel(e);
        }
    }

    private void handleVisitorLocations(InventoryClickEvent e)
    {
        e.setCancelled(true);
        Player player = (Player) e.getWhoClicked();
        ItemStack stack = e.getCurrentItem();
        String pageDisplay;
        try
        {
            pageDisplay = e.getClickedInventory().getItem(1).getItemMeta().getDisplayName();
        }
        catch (NullPointerException | IndexOutOfBoundsException ex)
        {
            return;
        }
        int page = Integer.valueOf(pageDisplay.substring(7));
        if (stack == null)
            return;
        if (!stack.hasItemMeta())
            return;
        if (e.getRawSlot() >= 10 && e.getRawSlot() <= 43)
        {
            if (!stack.getItemMeta().hasLore())
                return;
            VisitorLocation vl = VisitorLocation.getLocation(stack.getItemMeta().getLore().get(stack.getItemMeta().getLore().size() - 1).substring(2));
            if (vl == null)
            {
                player.closeInventory();
                player.sendMessage(ChatColor.RED + "Something went wrong!");
                return;
            }
            new ConfirmTravelGUI(player, vl).open(player);
            return;
        }
        if (stack.getItemMeta().hasDisplayName())
        {
            String name = stack.getItemMeta().getDisplayName();
            if (name.equals(ChatColor.AQUA + "Previous Page") && e.getRawSlot() == 46)
            {
                new VisitorLocationsGUI(page - 1).open(player);
                return;
            }
            if (name.equals(ChatColor.AQUA + "Next Page") && e.getRawSlot() == 52)
            {
                new VisitorLocationsGUI(page + 1).open(player);
                return;
            }
            if (name.equals(ChatColor.AQUA + "Exit") && e.getRawSlot() == 49)
            {
                player.closeInventory();
            }
        }
    }

    private void handleConfirmTravel(InventoryClickEvent e)
    {
        e.setCancelled(true);
        Player player = (Player) e.getWhoClicked();
        Inventory inv = e.getInventory();
        ItemStack stack = e.getCurrentItem();
        if (stack == null)
            return;
        if (!stack.hasItemMeta())
            return;
        if (stack.getItemMeta().hasDisplayName())
        {
            String name = stack.getItemMeta().getDisplayName();
            if (name.equals(ChatColor.GREEN + "CONFIRM"))
            {
                VisitorLocation vl = VisitorLocation.getLocation(inv.getItem(22).getItemMeta().getLore().get(inv.getItem(22).getItemMeta().getLore().size() - 1).substring(2));
                player.closeInventory();
                player.teleport(vl.getLocation());
                player.sendMessage(ChatColor.GREEN + "You have been transported to " + SUtil.color(vl.getName()) + ChatColor.GREEN + "!");
                return;
            }
            if (name.equals(ChatColor.RED + "CANCEL"))
            {
                player.closeInventory();
                player.sendMessage(ChatColor.RED + "Visit cancelled!");
                return;
            }
        }
    }
}