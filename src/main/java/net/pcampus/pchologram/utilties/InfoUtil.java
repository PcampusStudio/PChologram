package net.pcampus.pchologram.utilties;

import net.pcampus.pchologram.PChologram;

public class InfoUtil {

    public static String getInfoString() {
        PChologram hologram = PChologram.getInstance();
        return "PChologram version " + hologram.getDescription().getVersion() + " by " + ChatUtil.format(hologram.getDescription().getAuthors());

    }
}
