package net.pcampus.pchologram;

import lombok.Getter;
import net.md_5.bungee.api.ChatColor;
import net.pcampus.pchologram.commands.PChologramCommand;
import net.pcampus.pchologram.object.Hologram;
import net.pcampus.pchologram.utilties.FileManager;
import org.bstats.bukkit.Metrics;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.IOException;
import java.util.Properties;
import java.util.logging.Logger;

public final class PChologram extends JavaPlugin {

    @Getter private static Metrics metrics = null;

    private static PChologram instance;

    public static Logger logger() {
        return instance.getLogger();
    }

    private String version = "null";

    @Override
    public void onEnable() {
        instance = this;

        final Properties properties = new Properties();
        try {
            properties.load(this.getClassLoader().getResourceAsStream("PChologram.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        version = properties.getProperty("version");

        FileManager.gc().setup(this);

        new PChologramCommand(this).register();

        metrics = new Metrics(this, 15671);

        getServer().getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&',
                "\n" +
                        "&a|--------------<PChologram has been enabled>-------------|\n" +
                        "&a| Version: "+ version +"                                         |\n" +
                        "&a| Plugin by Pcampus Studio (studio.pcampus.net)          |\n" +
                        "&a| Develop by Garfieldcmix, PcampusNetwork                |\n" +
                        "&a|--------------------------------------------------------|\n" +
                        "&4 ________  ________  ___  ___  ________  ___       ________  ________  ________  ________  _____ ______      \n" +
                        "&6|\\   __  \\|\\   ____\\|\\  \\|\\  \\|\\   __  \\|\\  \\     |\\   __  \\|\\   ____\\|\\   __  \\|\\   __  \\|\\   _ \\  _   \\    \n" +
                        "&e\\ \\  \\|\\  \\ \\  \\___|\\ \\  \\\\\\  \\ \\  \\|\\  \\ \\  \\    \\ \\  \\|\\  \\ \\  \\___|\\ \\  \\|\\  \\ \\  \\|\\  \\ \\  \\\\\\__\\ \\  \\   \n" +
                        "&a \\ \\   ____\\ \\  \\    \\ \\   __  \\ \\  \\\\\\  \\ \\  \\    \\ \\  \\\\\\  \\ \\  \\  __\\ \\   _  _\\ \\   __  \\ \\  \\\\|__| \\  \\  \n" +
                        "&1  \\ \\  \\___|\\ \\  \\____\\ \\  \\ \\  \\ \\  \\\\\\  \\ \\  \\____\\ \\  \\\\\\  \\ \\  \\|\\  \\ \\  \\\\  \\\\ \\  \\ \\  \\ \\  \\    \\ \\  \\ \n" +
                        "&9   \\ \\__\\    \\ \\_______\\ \\__\\ \\__\\ \\_______\\ \\_______\\ \\_______\\ \\_______\\ \\__\\\\ _\\\\ \\__\\ \\__\\ \\__\\    \\ \\__\\\n" +
                        "&5    \\|__|     \\|_______|\\|__|\\|__|\\|_______|\\|_______|\\|_______|\\|_______|\\|__|\\|__|\\|__|\\|__|\\|__|     \\|__|\n"));

        Hologram.loadHologram();
    }

    @Override
    public void onDisable() {
        Hologram.unLoadHologram();

        instance = null;
    }

    public static PChologram getInstance() {
        return instance;
    }
}
