package me.pcampus.pchologram.commands;

import me.pcampus.pchologram.object.HologramUser;

public abstract class SubCommand {

    public abstract void execute(HologramUser user, String[] arts);

}
