package net.pcampus.pchologram.commands.subcommands;

import net.pcampus.pchologram.PChologram;
import net.pcampus.pchologram.commands.PChologramCommand;
import net.pcampus.pchologram.commands.SubCommand;
import net.pcampus.pchologram.object.HologramUser;
import org.bukkit.command.CommandSender;

public class HelpCommand extends SubCommand {
    HelpCommand(PChologram plugin, PChologramCommand cmd) {
        super(plugin, cmd, "help");
    }

    @Override
    public void onExecute(CommandSender sender, String[] args) {
        HologramUser user = new HologramUser(sender);

        int page;
        if (args.length == 0) {
            //send page one
            page = 1;
        } else {
            try {
                page = Integer.parseInt(args[0]);
            } catch (Exception ex) {
                //send page one
                page = 1;
            }
        }
        // Send help via page int
        sendHelp(user, page);
    }

    private void sendHelp(HologramUser user, int page) {
        user.sendMessageWithPrefix("&c&lComming soon! (Requested page " + page + ")");
    }
}
