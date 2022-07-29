package net.pcampus.pchologram.commands.subcommands;

import net.pcampus.pchologram.PChologram;
import net.pcampus.pchologram.commands.PChologramCommand;
import net.pcampus.pchologram.commands.SubCommand;
import net.pcampus.pchologram.object.Hologram;
import net.pcampus.pchologram.object.HologramUser;
import net.pcampus.pchologram.utilties.FileManager;
import org.bukkit.command.CommandSender;

public class ReloadCommand extends SubCommand {
	ReloadCommand(PChologram plugin, PChologramCommand cmd) {
		super(plugin, cmd, "reload");
	}

	@Override
	public void onExecute(CommandSender sender, String[] args) {
		HologramUser user = new HologramUser(sender);

		if (!user.getCommandSender().hasPermission("pchologram.command.reload")) {
			user.sendMessageWithPrefix("&cYou don't have permission to use this command!");
			return;
		}

		FileManager.gc().reload();

		Hologram.unLoadHologram();
		Hologram.loadHologram();
	}

}