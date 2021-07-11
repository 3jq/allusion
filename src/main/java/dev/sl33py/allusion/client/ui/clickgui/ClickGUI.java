package dev.sl33py.allusion.client.ui.clickgui;

import dev.sl33py.allusion.api.utils.Wrapper;
import dev.sl33py.allusion.client.module.Module;
import dev.sl33py.allusion.client.module.modules.client.ClickGuiModule;
import net.minecraft.client.gui.DrawableHelper;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.util.InputUtil;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.text.LiteralText;
import org.lwjgl.glfw.GLFW;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class ClickGUI extends Screen {

    public static List<Frame> frames;
    public static Color color;

    public ClickGUI() {
        super(new LiteralText("Allusion"));
        frames = new ArrayList<Frame>();
        int frameX = 10;
        for (final Module.Category category : Module.Category.values()) {
            final Frame frame = new Frame(category);
            frame.setX(frameX);
            ClickGUI.frames.add(frame);
            frameX += frame.getWidth() + 10;
        }
    }

    @Override
    public boolean isPauseScreen() { return false; }

    @Override
    public void render(MatrixStack matrices, int mouseX, int mouseY, float partialTicks) {
        color = new Color(ClickGuiModule.INSTANCE.r.getValue().intValue(), ClickGuiModule.INSTANCE.g.getValue().intValue(), ClickGuiModule.INSTANCE.b.getValue().intValue());
        if(ClickGuiModule.INSTANCE.tint.getValue())
            DrawableHelper.fill(new MatrixStack(), 0, 0, Wrapper.mc.getWindow().getScaledWidth(), Wrapper.mc.getWindow().getScaledHeight(), new Color(0, 0, 0, 100).getRGB());
        frames.forEach(frame -> {
            frame.renderFrame();
            frame.updatePosition(mouseX, mouseY);
            frame.getComponents().forEach(c -> c.updateComponent(mouseX, mouseY));
        });
    }

    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int mouseButton) {
        for (final Frame frame : frames) {
            if (frame.isHover(mouseX, mouseY) && mouseButton == 0) {
                frame.setDrag(true);
                frame.dragX = (int) (mouseX - frame.getX());
                frame.dragY = (int) (mouseY - frame.getY());
            }
            if (frame.isHover(mouseX, mouseY) && mouseButton == 1) frame.setOpen(!frame.isOpen());

            if (frame.isOpen() && !frame.getComponents().isEmpty()) {
                for (final Component component : frame.getComponents()) {
                    component.mouseClicked(mouseX, mouseY, mouseButton);
                }
            }
        }
        return false;
    }

    @Override
    public boolean mouseReleased(double mouseX, double mouseY, int state) {
        for (final Frame frame : frames) {
            frame.setDrag(false);
        }
        for (final Frame frame : frames) {
            if (frame.isOpen() && !frame.getComponents().isEmpty()) {
                for (final Component component : frame.getComponents()) {
                    component.mouseReleased(mouseX, mouseY, state);
                }
            }
        }
        return false;
    }

    public void drawGradient(int left, int top, int right, int bottom, int startColor, int endColor) {
        this.fillGradient(new MatrixStack(), left, top, right, bottom, startColor, endColor);
    }

    @Override
    public boolean keyPressed(int keyCode, int scanCode, int modifiers) {
        for (final Frame frame : ClickGUI.frames) {
            if (frame.isOpen() && keyCode != 1 && !frame.getComponents().isEmpty()) {
                for (final Component component : frame.getComponents()) {
                    component.keyTyped(keyCode);
                }
            }
        }

        if(keyCode == GLFW.GLFW_KEY_ESCAPE) {
            Wrapper.mc.openScreen(null);
            ClickGuiModule.INSTANCE.toggle();
        }
        return super.keyPressed(keyCode, scanCode, modifiers);
    }

}
