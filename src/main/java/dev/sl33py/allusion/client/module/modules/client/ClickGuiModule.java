package dev.sl33py.allusion.client.module.modules.client;

import dev.sl33py.allusion.Allusion;
import dev.sl33py.allusion.api.setting.Setting;
import dev.sl33py.allusion.client.module.Module;
import org.lwjgl.glfw.GLFW;

import java.util.Arrays;

public class ClickGuiModule extends Module {

    public static ClickGuiModule INSTANCE;
    public Setting<Double> r = register("Red", 155, 0, 255, 0);
    public Setting<Double> g = register("Green", 0, 0, 255, 0);
    public Setting<Double> b = register("Blue", 255, 0, 255, 0);
    public Setting<Boolean> tint = register("Tint", true);

    public ClickGuiModule() {
        super("ClickGui", "Shows client ClickGui", Category.Client);
        setKey(GLFW.GLFW_KEY_INSERT);
        INSTANCE = this;
    }

    @Override public void onEnable() {
        if (mc.player != null) mc.openScreen(Allusion.clickGui);
    }

    @Override public void onDisable() {
        if (mc.player != null) mc.openScreen(null);
    }

}
