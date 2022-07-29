package net.pcampus.pchologram.commands.subcommands;

import net.pcampus.pchologram.PChologram;
import net.pcampus.pchologram.commands.PChologramCommand;
import net.pcampus.pchologram.commands.SubCommand;
import net.pcampus.pchologram.object.Hologram;
import net.pcampus.pchologram.object.HologramLocation;
import net.pcampus.pchologram.object.HologramUser;
import net.pcampus.pchologram.utilties.ChatUtil;
import org.bukkit.command.CommandSender;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class MoveCommand extends SubCommand {
	MoveCommand(PChologram plugin, PChologramCommand cmd) {
		super(plugin, cmd, "move");
	}

	@Override
	public void onExecute(CommandSender sender, String[] args) {
		HologramUser user = new HologramUser(sender);

		if (!user.isPlayer()) {
			user.sendMessageWithPrefix("&cYou must be a player to execute this command.");
			return;
		}

		if (args.length <= 4 ) {
			user.sendMessageWithPrefix("&c/pcholo move <name> <x> <y> <z>");
			return;
		}

		String name = args[1];

		if (!(  (ChatUtil.isStringDouble(args[2]) || Objects.equals(args[2], "~")) &&
				(ChatUtil.isStringDouble(args[3]) || Objects.equals(args[3], "~")) &&
				(ChatUtil.isStringDouble(args[4]) || Objects.equals(args[4], "~"))
		)) {
			user.sendMessageWithPrefix("&c/pcholo move <name> <x> <y> <z>");
			return;
		}

		double x;
		double y;
		double z;

		if (Objects.equals(args[2], "~")) {
			x = user.accessPlayer().getLocation().getX();
		} else {
			x = Double.parseDouble(args[2]);
		}
		if (Objects.equals(args[3], "~")) {
			y = user.accessPlayer().getLocation().getY();
		} else {
			y = Double.parseDouble(args[3]);
		}
		if (Objects.equals(args[4], "~")) {
			z = user.accessPlayer().getLocation().getZ();
		} else {
			z = Double.parseDouble(args[4]);
		}

		HologramLocation location = new HologramLocation(user.accessPlayer().getWorld(), x, y, z);

		Hologram hologram = new Hologram(name);
		if (!hologram.isCreated()) {
			user.sendMessageWithPrefix("&cThat name doesn't exist!");
		} else {
			hologram.move(location);
			user.sendMessageWithPrefix("&aMoved hologram.");
		}
	}

}