package me.pcampus.pchologram.commands.subcommands;

import me.pcampus.pchologram.commands.SubCommand;
import me.pcampus.pchologram.object.Hologram;
import me.pcampus.pchologram.object.HologramUser;

import java.util.ArrayList;
import java.util.List;

public class CreateSubCommand extends SubCommand {


    @Override
    public void execute(HologramUser user, String[] args) {

        if (!user.isPlayer()) {
            user.sendMessageWithPrefix("&cYou must be a player to execute this command.");
            return;
        }

        if (args.length == 0 ) {
            user.sendMessageWithPrefix("&c/pcholo create <name> <content>");
            return;
        }

        String name = args[0];

        if (args.length <= 1) {
            user.sendMessageWithPrefix("&c/pcholo create <name> <content>");
            return;
        }

        List<String> list = new ArrayList<>();
        for (String string : args) {
            if (string.equals(name)) {
                continue;
            }
            list.add(string);
        }
        new Hologram(user.accessPlayer().getLocation(), list);
        user.sendMessageWithPrefix("&aCreated debug hologram.");
    }

}
