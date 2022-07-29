package net.pcampus.pchologram.commands.subcommands;

import net.pcampus.pchologram.PChologram;
import net.pcampus.pchologram.commands.PChologramCommand;
import net.pcampus.pchologram.commands.SubCommand;
import net.pcampus.pchologram.object.Hologram;
import net.pcampus.pchologram.object.HologramUser;
import net.pcampus.pchologram.utilties.ChatUtil;
import org.bukkit.command.CommandSender;

public class RemovelineCommand extends SubCommand {
	RemovelineCommand(PChologram plugin, PChologramCommand cmd) {
		super(plugin, cmd, "removeline");
	}

	@Override
	public void onExecute(CommandSender sender, String[] args) {
		HologramUser user = new HologramUser(sender);

		if (!user.isPlayer()) {
			user.sendMessageWithPrefix("&cYou must be a player to execute this command.");
			return;
		}

		if (args.length <= 2 ) {
			user.sendMessageWithPrefix("&c/pcholo removeline <name> [line]");
			return;
		}

		String name = args[1];

		if (args.length == 3) {
			if (!ChatUtil.isStringInt(args[2])) {
				user.sendMessageWithPrefix("&cInvalid Input!");
				return;
			}

			int index = Integer.parseInt(args[2]) - 1;

			Hologram hologram = new Hologram(name);

			if (!hologram.isCreated()) {
				user.sendMessageWithPrefix("&cThat name doesn't exist!");
				return;
			}

			if (hologram.isIndexOut(index)) {
				user.sendMessageWithPrefix("&cThat line doesn't exist!");
				return;
			}

			hologram.removeContent(index);
			user.sendMessageWithPrefix("&aRemoved line " + (index + 1) + " from \"" + name + "\".");
		}
	}

}