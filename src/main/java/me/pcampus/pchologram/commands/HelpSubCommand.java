package me.pcampus.pchologram.commands;

import me.pcampus.pchologram.object.HoloUser;

public class HelpSubCommand extends SubCommand {

    public void execute(HoloUser user, String[] args) {
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

    private void sendHelp(HoloUser user, int page) {
        user.sendMessageWithPrefix("&c&lComming soon! (Requested page " + page + ")");
    }
}
