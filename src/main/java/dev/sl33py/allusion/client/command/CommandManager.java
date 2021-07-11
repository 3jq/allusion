package dev.sl33py.allusion.client.command;

import dev.sl33py.allusion.api.utils.chat.MessageUtil;
import net.minecraft.util.Formatting;
import org.reflections.Reflections;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Set;

public class CommandManager {

    public ArrayList<Command> commands;

    public static String prefix = "^";

    public CommandManager() {
        commands = new ArrayList<>();

        /**
         * Iterating every command, adding it to massive.
         */
        Set<Class<? extends Command>> reflections = new Reflections("dev.sl33py.allusion.client.command.commands").getSubTypesOf(Command.class);
        reflections.forEach(aClass -> {
            try {
                commands.add(aClass.newInstance());
            } catch (InstantiationException | IllegalAccessException e) {
                e.printStackTrace();
            }
        });

        /**
         * Sorting the commands alphabetically.
         */
        commands.sort(Comparator.comparing(object -> object.name));
    }

    public void handleCommand(String commandName) {
        boolean found = false;
        String message = commandName.substring(prefix.length());
        String[] args = commandName.split(" ");

        String originalName = message.split(" ")[0];
        for (Command command : commands) {
            if (command.name.equalsIgnoreCase(originalName) || command.aliases.contains(originalName)) {
                found = true;
                command.handle(Arrays.copyOfRange(message.split(" "), 1, message.split(" ").length));
                return;
            }
        }

        if (!found) {
            MessageUtil.addChatMessage(Formatting.GREEN + "Cannot find command " + commandName + "! For list of commands, use " + prefix + "help");
        }
    }
}
