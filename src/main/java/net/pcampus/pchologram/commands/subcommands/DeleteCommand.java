package net.pcampus.pchologram.commands.subcommands;

import net.pcampus.pchologram.PChologram;
import net.pcampus.pchologram.commands.PChologramCommand;
import net.pcampus.pchologram.commands.SubCommand;
import net.pcampus.pchologram.object.Hologram;
import net.pcampus.pchologram.object.HologramLocation;
import net.pcampus.pchologram.object.HologramUser;
import org.bukkit.command.CommandSender;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DeleteCommand extends SubCommand {
	DeleteCommand(PChologram plugin, PChologramCommand cmd) {
		super(plugin, cmd, "delete");
	}

	@Override
	public void onExecute(CommandSender sender, String[] args) {
		HologramUser user = new HologramUser(sender);

		if (!user.isPlayer()) {
			user.sendMessageWithPrefix("&cYou must be a player to execute this command.");
			return;
		}

		if (args.length <= 1 ) {
			user.sendMessageWithPrefix("&c/pcholo delete <name>");
			return;
		}

		String name = args[1];

		Hologram hologram = new Hologram(name);
		if (!hologram.isCreated()) {
			user.sendMessageWithPrefix("&cThat name doesn't exist!");
		} else {
			hologram.delete();
			user.sendMessageWithPrefix("&aDeleted hologram.");
		}
	}

}
