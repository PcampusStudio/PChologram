package me.pcampus.pchologram;

import me.pcampus.pchologram.commands.HoloCommand;
import org.bukkit.plugin.java.JavaPlugin;

public final class PChologram extends JavaPlugin {

    private static PChologram instance;

    @Override
    public void onEnable() {
        // Plugin startup logic
        instance = this;

        getCommand("pcholo").setExecutor(new HoloCommand());

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        instance = null;
    }

    public static PChologram getInstance() {
        return instance;
    }
}
