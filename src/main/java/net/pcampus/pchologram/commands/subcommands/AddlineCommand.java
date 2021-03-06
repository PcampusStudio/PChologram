package net.pcampus.pchologram.commands.subcommands;

import net.pcampus.pchologram.PChologram;
import net.pcampus.pchologram.commands.PChologramCommand;
import net.pcampus.pchologram.commands.SubCommand;
import net.pcampus.pchologram.object.Hologram;
import net.pcampus.pchologram.object.HologramUser;
import net.pcampus.pchologram.utilties.ChatUtil;
import org.bukkit.command.CommandSender;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AddlineCommand extends SubCommand {
	AddlineCommand(PChologram plugin, PChologramCommand cmd) {
		super(plugin, cmd, "addline");
	}

	@Override
	public void onExecute(CommandSender sender, String[] args) {
		HologramUser user = new HologramUser(sender);

		if (!user.isPlayer()) {
			user.sendMessageWithPrefix("&cYou must be a player to execute this command.");
			return;
		}

		if (args.length <= 2 ) {
			user.sendMessageWithPrefix("&c/pcholo addline <name> [line] <content>");
			return;
		}

		String name = args[1];

		List<String> argsList = new ArrayList<>(Arrays.asList(args));
		argsList.remove(0);
		argsList.remove(0);

		if (args.length == 3) {
			StringBuilder content = new StringBuilder("");
			for (String string : argsList) {
				content.append(string).append(" ");
			}
			content.deleteCharAt(content.length() - 1);

			Hologram hologram = new Hologram(name);
			if (!hologram.isCreated()) {
				user.sendMessageWithPrefix("&cThat name doesn't exist!");
			} else {
				hologram.addContent(content.toString());
				user.sendMessageWithPrefix("&aAdded new line to \"" + name + "\".");
			}
		} else if (args.length == 4) {
			if (!ChatUtil.isStringInt(args[2])) {
				user.sendMessageWithPrefix("&c/pcholo addline <name> [line] <content>");
				return;
			}
			int line = Integer.parseInt(args[2]) - 1;

			argsList.remove(0);

			StringBuilder content = new StringBuilder("");
			for (String string : argsList) {
				content.append(string).append(" ");
			}
			content.deleteCharAt(content.length() - 1);

			Hologram hologram = new Hologram(name);
			if (!hologram.isCreated()) {
				user.sendMessageWithPrefix("&cThat name doesn't exist!");
			} else {
				if (hologram.isIndexOut(line)) {
					user.sendMessageWithPrefix("That line is out of range!");
				}
				hologram.addContent(line, content.toString());
				user.sendMessageWithPrefix("&aAdded new line to \"" + name + "\".");
			}
		} else {
			user.sendMessageWithPrefix("&c/pcholo addline <name> [line] <content>");
		}
	}

}