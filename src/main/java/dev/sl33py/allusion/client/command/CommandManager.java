package dev.sl33py.allusion.client.command;

import dev.sl33py.allusion.api.utils.chat.MessageUtil;
import net.minecraft.util.Formatting;
import org.reflections.Reflections;

import java.util.ArrayList;
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
        String originalName;

        for (Command command : commands) {
            originalName = prefix + command.name;
            if (originalName.equalsIgnoreCase(commandName)) {
                found = true;
                command.handle();
                return;
            }

            for (String alias : command.aliases) {
                originalName = prefix + alias;

                if (originalName.equalsIgnoreCase(commandName)) {
                    found = true;
                    command.handle();
                    return;
                }
            }
        }

        if (!found) {
            MessageUtil.addChatMessage(Formatting.GREEN + "Cannot find command " + commandName + "! For list of commands, use " + prefix + "help");
        }
    }
}
