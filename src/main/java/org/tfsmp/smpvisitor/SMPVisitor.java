package org.tfsmp.smpvisitor;

import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.tfsmp.smpvisitor.command.Command_visit;
import org.tfsmp.smpvisitor.command.Command_visitorlocations;
import org.tfsmp.smpvisitor.config.Config;
import org.tfsmp.smpvisitor.gui.GUIInteraction;
import org.tfsmp.smpvisitor.util.SLog;

public final class SMPVisitor extends JavaPlugin
{
    private static SMPVisitor plugin;
    public static SMPVisitor getPlugin()
    {
        return plugin;
    }

    public Config locations;

    @Override
    public void onEnable()
    {
        plugin = this;
        locations = new Config("locations.yml");
        loadCommands();
        loadListeners();
        SLog.info("Enabled.");
    }

    @Override
    public void onDisable()
    {
        plugin = null;
        SLog.info("Disabled.");
    }

    private void loadCommands()
    {
        this.getCommand("visit").setExecutor(new Command_visit());
        this.getCommand("visitorlocations").setExecutor(new Command_visitorlocations());
    }

    private void loadListeners()
    {
        PluginManager manager = this.getServer().getPluginManager();
        manager.registerEvents(new GUIInteraction(), this);
    }
}
