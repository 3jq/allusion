package dev.sl33py.allusion.client.command.commands;

import dev.sl33py.allusion.Allusion;
import dev.sl33py.allusion.api.utils.chat.MessageUtil;
import dev.sl33py.allusion.client.command.Command;

public class HelpCommand extends Command {

    public HelpCommand() {
        super("help", "help", "h", "commands", "commandlist", "commandslist", "cl");
    }

    @Override
    public void handle(String[] args) {
        String commandsList = "Commands (" + Allusion.commandManager.commands.size() + "): ";

        for (Command command : Allusion.commandManager.commands) {
            commandsList += command.name + ", ";
        }

        commandsList += ".";

        MessageUtil.addChatMessage(commandsList.substring(0, commandsList.length() - 3) + ".");
    }
}
