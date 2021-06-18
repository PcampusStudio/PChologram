package me.pcampus.pchologram.commands;

import me.pcampus.pchologram.object.HoloUser;

public abstract class SubCommand {

    public abstract void execute(HoloUser user, String[] arts);

}
