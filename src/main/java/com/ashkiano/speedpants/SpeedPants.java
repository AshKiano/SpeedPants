package com.ashkiano.speedpants;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

//TODO speed value nastavit příkazem na givnutí kalhot a uchovávat v lore
//TODO udělat nastavitelné lore
//TODO přidat craftící recept
//TODO přidat vustom materiál na příkaz
//TODO hrac o efekt mozna neprijde když umře, ale tím si nejsem jistý, třeba otestovat
public class SpeedPants extends JavaPlugin {

    private String permission;

    @Override
    public void onEnable() {
        this.saveDefaultConfig();
        FileConfiguration config = this.getConfig();
        permission = config.getString("command_permission", "speedpants");

        getCommand("speedpants").setExecutor(new SpeedPantsCommand(permission, this));
        getServer().getPluginManager().registerEvents(new SpeedPantsListener(this), this);

        Metrics metrics = new Metrics(this, 19029);
    }
}
