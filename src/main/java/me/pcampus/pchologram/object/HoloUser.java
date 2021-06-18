package me.pcampus.pchologram.object;

import me.pcampus.pchologram.utilties.ChatUtil;
import org.bukkit.command.CommandSender;

public class HoloUser {

    private CommandSender commandSender;
    public HoloUser(CommandSender commandSender) {
        this.commandSender = commandSender;
    }

    public CommandSender getCommandSender() {
        return commandSender;
    }

    public void sendMessage(String message) {
        getCommandSender().sendMessage(ChatUtil.format(message));
    }
    public void sendMessageWithPrefix(String message) {
        sendMessage("&b>&9> &3&lPCHolo &9<&b< &7: &f" + message);
    }
}
