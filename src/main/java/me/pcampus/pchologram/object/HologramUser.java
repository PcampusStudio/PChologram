package me.pcampus.pchologram.object;

import me.pcampus.pchologram.utilties.ChatUtil;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class HologramUser {

    private CommandSender commandSender;
    public HologramUser(CommandSender commandSender) {
        this.commandSender = commandSender;
    }

    public CommandSender getCommandSender() {
        return commandSender;
    }

    public void sendMessage(String message) {
        getCommandSender().sendMessage(ChatUtil.format(message));
    }
    public void sendMessageWithPrefix(String message) {
        sendMessage("&3&lPCHolo &9>&b> &7: &f" + message);
    }
    public boolean isPlayer() {
        return commandSender instanceof Player;
    }

    public Player accessPlayer() {
        return (Player) commandSender;
    }
}
