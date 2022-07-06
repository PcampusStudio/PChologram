package net.pcampus.pchologram.commands;

import net.pcampus.pchologram.PChologram;
import net.pcampus.pchologram.commands.subcommands.PChologramSubCommand;
import net.pcampus.pchologram.object.HologramUser;
import net.pcampus.pchologram.utilties.InfoUtil;
import org.apache.commons.lang.Validate;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.event.Listener;

import java.util.*;

public class PChologramCommand implements CommandExecutor, Listener {

    private final PChologram plugin;
    private boolean registered = false;
    private final List<SubCommand> commands = new LinkedList<>();
    private final Map<SubCommand, Integer> commandUsage = new HashMap<>();

    public PChologramCommand(PChologram plugin) {
        this.plugin = plugin;
    }

    public void register() {
        Validate.isTrue(!registered, "PCbankCommand have already been registered!");

        registered = true;
        plugin.getServer().getPluginManager().registerEvents(this, plugin);

        plugin.getCommand("pcholo").setExecutor(this);
        plugin.getCommand("pcholo").setTabCompleter(new PChologramTabCompleter());
        commands.addAll(PChologramSubCommand.getAllCommands(this));
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        HologramUser user = new HologramUser(sender);

        if (args.length > 0) {
            for (SubCommand command : commands) {
                if (args[0].equalsIgnoreCase(command.getName())) {
                    command.recordUsage(commandUsage);
                    command.onExecute(sender, args);
                    return true;
                }
            }
        } else {
            user.sendMessageWithPrefix("&f" + InfoUtil.getInfoString());
        }
        return !commands.isEmpty();
    }

    public PChologram getPlugin() {
        return plugin;
    }
}
