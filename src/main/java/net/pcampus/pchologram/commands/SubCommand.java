package net.pcampus.pchologram.commands;

import net.pcampus.pchologram.PChologram;
import org.bukkit.command.CommandSender;

import java.util.Map;

public abstract class SubCommand {

	protected final PChologram plugin;
	protected final PChologramCommand cmd;

	private final String name;

	protected SubCommand(PChologram plugin, PChologramCommand cmd, String name) {
		this.plugin = plugin;
		this.cmd = cmd;

		this.name = name;
	}

	public final String getName() {
		return name;
	}

	protected void recordUsage(Map<SubCommand, Integer> commandUsage) {
		commandUsage.merge(this, 1, Integer::sum);
	}

	public abstract void onExecute(CommandSender sender, String[] args);
}