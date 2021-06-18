package me.pcampus.pchologram.utilties;

import me.pcampus.pchologram.PChologram;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public class FileUtil {
    private static FileUtil ourInstance = new FileUtil();
    public static FileUtil getInstance() {
        return ourInstance;
    }
    private File dataFile;
    private FileConfiguration dataConfig;

    private FileUtil() {
        this.dataFile = new File(PChologram.getInstance().getDataFolder(), "data.yml");

        if (!this.dataFile.exists()) {
            try {
                this.dataFile.createNewFile();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        this.dataConfig = YamlConfiguration.loadConfiguration(this.dataFile);

    }

    public FileConfiguration getDataConfig() {
        return dataConfig;
    }

    public void saveData() {
        try {
            this.dataConfig.save(this.dataFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



}
