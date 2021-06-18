package me.pcampus.pchologram.utilties;

import org.bukkit.ChatColor;

import java.util.List;

public class ChatUtil {

    public static String format(String string) {
        return ChatColor.translateAlternateColorCodes('&',string);

    }

    public static String format(List<String> list) {
        String str = "";
        for (String content : list) {
            if (str.equals("")) {
                str = content;
            } else {
                str = str + ", " + content;
            }
        }
        return str;
    }

    public static String convertIntoStringWithNewLines(List<String> list) {
        String str = "";
        for (String content : list) {
            if (str.equals("")) {
                str = content;
            } else {
                str = str + "\n" + content;
            }
        }
        return str;
    }

    // ["hello, NattaChannel", "Welcome to Pcampus Network"]
    // change to "hello, NattaChannel\nWelcome to Pcampus Network

}
