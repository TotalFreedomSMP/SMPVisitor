package org.tfsmp.smpvisitor.config;

import org.bukkit.configuration.file.YamlConfiguration;
import org.tfsmp.smpvisitor.SMPVisitor;

import java.io.File;

public class Config extends YamlConfiguration
{
    private final SMPVisitor plugin;
    private final File file;

    public Config(String name)
    {
        this.plugin = SMPVisitor.getPlugin();
        this.file = new File(plugin.getDataFolder(), name);

        if (!file.exists())
        {
            options().copyDefaults(true);
            plugin.saveResource(name, false);
        }
        load();
    }

    public void load()
    {
        try
        {
            super.load(file);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public void save()
    {
        try
        {
            super.save(file);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}