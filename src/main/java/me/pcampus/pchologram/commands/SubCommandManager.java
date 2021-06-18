package me.pcampus.pchologram.commands;

import me.pcampus.pchologram.commands.subcommands.CreateSubCommand;
import me.pcampus.pchologram.commands.subcommands.HelpSubCommand;

import java.util.HashMap;
import java.util.Map;

public class SubCommandManager {

    private static SubCommandManager ourInstance = new SubCommandManager();
    public static SubCommandManager getInstance() { return ourInstance; }

    private SubCommandManager() {
        // TODO add sub commands
        subCommands.put("help",new HelpSubCommand());
        subCommands.put("create",new CreateSubCommand());


    }

    private Map<String, SubCommand> subCommands = new HashMap<>();

    public SubCommand find(String str) {
        return subCommands.get(str);
    }

}
