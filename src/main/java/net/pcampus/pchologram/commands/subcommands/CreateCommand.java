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

public class CreateCommand extends SubCommand {
    CreateCommand(PChologram plugin, PChologramCommand cmd) {
        super(plugin, cmd, "create");
    }

    @Override
    public void onExecute(CommandSender sender, String[] args) {
        HologramUser user = new HologramUser(sender);

        if (!user.isPlayer()) {
            user.sendMessageWithPrefix("&cYou must be a player to execute this command.");
            return;
        }

        if (args.length <= 2 ) {
            user.sendMessageWithPrefix("&c/pcholo create <name> <content>");
            return;
        }

        String name = args[1];

        List<String> argsList = new ArrayList<>(Arrays.asList(args));
        argsList.remove(0); argsList.remove(0);

        StringBuilder content = new StringBuilder("");
        for (String string : argsList) {
            content.append(string).append(" ");
        }
        content.deleteCharAt(content.length() - 1);

        HologramLocation location = new HologramLocation(user.accessPlayer().getLocation());

        Hologram hologram = new Hologram(location, name, content.toString());
        if (hologram.isCreated()) {
            user.sendMessageWithPrefix("&cThat name already exist!");
        } else {
            hologram.create();
            user.sendMessageWithPrefix("&aCreated hologram.");
        }
    }

}
