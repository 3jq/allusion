package dev.sl33py.allusion.client.command.commands;

import dev.sl33py.allusion.Allusion;
import dev.sl33py.allusion.api.utils.chat.MessageUtil;
import dev.sl33py.allusion.client.command.Command;
import dev.sl33py.allusion.client.command.CommandManager;
import dev.sl33py.allusion.client.module.Module;
import net.minecraft.util.Formatting;

public class ToggleCommand extends Command {

    public ToggleCommand() {
        super("toggle", "toggle <module>", "t");
    }

    @Override
    public void handle(String[] args) {
        if (args.length < 1) {
            MessageUtil.addChatMessage("Not enough arguments! Usage: " + CommandManager.prefix + usage);
            return;
        }

        boolean found = false;

        Module module = Allusion.moduleManager.getModule(args[0]);

        if (module != null) {
            found = true;
            module.toggle();
            MessageUtil.addChatMessage(module.isEnabled() ? Formatting.GREEN + module.getName() + " is now enabled." : Formatting.RED + module.getName() + " is now disabled.");
        }

        if (!found) {
            MessageUtil.addChatMessage("Module not found!");
        }
     }
}
