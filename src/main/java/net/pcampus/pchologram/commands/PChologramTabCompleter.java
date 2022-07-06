package net.pcampus.pchologram.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

import java.util.*;

class PChologramTabCompleter implements TabCompleter {

	private static final int MAX_SUGGESTIONS = 80;

	@Override
	public List<String> onTabComplete(CommandSender sender, Command cmd, String label, String[] args) {
		if (args.length == 1) {
			List<String> Commands = new ArrayList<>(Collections.emptyList());

			// Add Command Completion here

			return createReturnList(Commands, args[0]);
		}

		return null;
	}

	private List<String> createReturnList(List<String> list, String string) {
		if (string.length() == 0) {
			return list;
		}

		String input = string.toLowerCase(Locale.ROOT);
		List<String> returnList = new LinkedList<>();

		for (String item : list) {
			if (item.toLowerCase(Locale.ROOT).contains(input)) {
				returnList.add(item);

				if (returnList.size() >= MAX_SUGGESTIONS) {
					break;
				}
			} else if (item.equalsIgnoreCase(input)) {
				return Collections.emptyList();
			}
		}

		return returnList;
	}
}
