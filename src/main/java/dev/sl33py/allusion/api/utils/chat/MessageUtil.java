package dev.sl33py.allusion.api.utils.chat;

import dev.sl33py.allusion.Allusion;
import net.minecraft.client.MinecraftClient;
import net.minecraft.text.LiteralText;

public class MessageUtil {

    static final MinecraftClient mc = MinecraftClient.getInstance();

    public static void addChatMessage(String message) {
        String prefix = Formatting.AQUA + "<" + Allusion.name + ">" + Formatting.RESET + " ";

        mc.inGameHud.getChatHud().addMessage(new LiteralText(prefix + message));
    }
}
