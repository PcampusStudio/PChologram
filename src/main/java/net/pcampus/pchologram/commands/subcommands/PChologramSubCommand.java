package net.pcampus.pchologram.commands.subcommands;

import net.pcampus.pchologram.PChologram;
import net.pcampus.pchologram.commands.PChologramCommand;
import net.pcampus.pchologram.commands.SubCommand;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

public class PChologramSubCommand {

	private PChologramSubCommand() {}

	public static Collection<SubCommand> getAllCommands(PChologramCommand cmd) {
		PChologram plugin = cmd.getPlugin();
		List<SubCommand> commands = new LinkedList<>();

		commands.add(new CreateCommand(plugin, cmd));
		commands.add(new DeleteCommand(plugin, cmd));
		commands.add(new AddlineCommand(plugin, cmd));
		commands.add(new RemovelineCommand(plugin, cmd));
		commands.add(new MoveCommand(plugin, cmd));
		commands.add(new HelpCommand(plugin, cmd));
		commands.add(new ReloadCommand(plugin, cmd));

		return commands;
	}
}
