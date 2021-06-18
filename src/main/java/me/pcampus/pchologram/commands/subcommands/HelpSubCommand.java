package me.pcampus.pchologram.commands.subcommands;

import me.pcampus.pchologram.commands.SubCommand;
import me.pcampus.pchologram.object.HologramUser;

public class HelpSubCommand extends SubCommand {

    public void execute(HologramUser user, String[] args) {
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
