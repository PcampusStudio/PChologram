package me.pcampus.pchologram.commands;

import me.pcampus.pchologram.object.HologramUser;
import me.pcampus.pchologram.utilties.InfoUtil;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import java.util.ArrayList;
import java.util.List;

public class HoloCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        HologramUser user = new HologramUser(sender);

        if (args.length == 0) {
            // User wants help argument
            user.sendMessageWithPrefix("&f" + InfoUtil.getInfoString());

        } else {
            SubCommand subCommand = SubCommandManager.getInstance().find(args[0]);
            if (subCommand == null) {
                subCommand = SubCommandManager.getInstance().find("help");
                if (subCommand == null) {
                    user.sendMessageWithPrefix("&cSomething went wrong.");
                    return true;
                }
            }

            List<String> newArgs = new ArrayList<>();

            for (int i=0; i < args.length; i++) {
                if (i == 0) {
                    continue;
                }
                newArgs.add(args[i]);
            }
            subCommand.execute(user,newArgs.toArray(new String[0]));
        }

        return false;
    }
}
