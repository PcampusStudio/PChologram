package net.pcampus.pchologram.utilties;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;

import java.io.File;
import java.io.IOException;

public class FileManager {
	private Plugin plugin;

	private File dataFile;
	private FileConfiguration dataConfig;

	static FileManager fileManager = new FileManager();
	public static FileManager gc() {
		return fileManager;
	}

	public void setup(Plugin plugin) {
		this.plugin = plugin;
		if (!plugin.getDataFolder().exists()) {
			plugin.getDataFolder().mkdir();
		}

		dataFile = new File(plugin.getDataFolder(), "data.yml"); checkFile(dataFile);
		dataConfig = YamlConfiguration.loadConfiguration(dataFile);

		if (!checkConfig(plugin)) {
			plugin.getServer().getPluginManager().disablePlugin(plugin);
		}
	}

	public void reload() {
		if (!plugin.getDataFolder().exists()) {
			plugin.getDataFolder().mkdir();
		}

		dataFile = new File(plugin.getDataFolder(), "data.yml"); checkFile(dataFile);
		dataConfig = YamlConfiguration.loadConfiguration(dataFile);

		if (!checkConfig(plugin)) {
			plugin.getServer().getPluginManager().disablePlugin(plugin);
		}
	}

	private boolean checkConfig(Plugin plugin) {
		return true;
	}

	private void checkFile(File file) {
		if ((!file.exists())) {
			try {
				file.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	private void saveConfig(File file, FileConfiguration fileConfig) {
		try {
			fileConfig.save(file);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}


	public FileConfiguration getDataConfig() {
		return this.dataConfig;
	}
	public void saveDataConfig() {
		saveConfig(this.dataFile, this.dataConfig);
	}

}