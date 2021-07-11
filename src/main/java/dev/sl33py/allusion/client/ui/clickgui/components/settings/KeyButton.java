package dev.sl33py.allusion.client.ui.clickgui.components.settings;

import dev.sl33py.allusion.api.setting.Setting;
import dev.sl33py.allusion.api.utils.Wrapper;
import dev.sl33py.allusion.client.ui.clickgui.Component;
import dev.sl33py.allusion.client.ui.clickgui.components.ModuleButton;
import net.minecraft.client.gui.DrawableHelper;
import net.minecraft.client.util.InputUtil;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.text.LiteralText;
import net.minecraft.text.Text;
import org.lwjgl.glfw.GLFW;

import java.awt.*;

public class KeyButton extends Component {

    private boolean binding;
    private final ModuleButton button;
    private boolean isHovered;
    private int offset;
    private int x;
    private int y;

    public KeyButton(final ModuleButton button, final int offset) {
        this.button = button;
        this.x = button.frame.getX() + button.frame.getWidth();
        this.y = button.frame.getY() + button.offset;
        this.offset = offset;
    }

    @Override public void setOffset(final int offset) {
        this.offset = offset;
    }

    @Override public void updateComponent(final double mouseX, final double mouseY) {
        isHovered = isHovered(mouseX, mouseY);
        y = button.frame.getY() + this.offset;
        x = button.frame.getX();
    }

    @Override public void mouseClicked(final double mouseX, final double mouseY, final int button) {
        if (isHovered(mouseX, mouseY) && button == 0 && this.button.open) {
            binding = !binding;
        }
    }

    @Override public void keyTyped(final int key) {
        if (this.binding) {
            if (key == GLFW.GLFW_KEY_DELETE) button.module.setKey(-1);
            else button.module.setKey(key);
            this.binding = false;
        }
    }

    @Override public void render() {
        DrawableHelper.fill(new MatrixStack(), button.frame.getX() + 1, button.frame.getY() + offset, button.frame.getX() + button.frame.getWidth() - 1, button.frame.getY() + offset + 16, isHovered ? new Color(0, 0, 0, 75).getRGB() : new Color(0, 0, 0, 60).getRGB());
        Wrapper.mc.textRenderer.draw(new MatrixStack(), "Key", button.frame.getX() + 5, button.frame.getY() + offset + 3, -1);
        if(binding) {
            Wrapper.mc.textRenderer.draw(new MatrixStack(), "...", button.frame.getX() + button.frame.getWidth() - 5 - Wrapper.mc.textRenderer.getWidth("..."), button.frame.getY() + offset + 3, -1);
        } else {
            Text keyString;
            switch (button.module.getKey()) {
                case 345:
                    keyString = new LiteralText("rCtrl");
                    break;
                case 341:
                    keyString = new LiteralText("Ctrl");
                    break;
                case 346:
                    keyString = new LiteralText("rAlt");
                    break;
                default:
                    keyString = InputUtil.fromKeyCode(button.module.getKey(), -1).getLocalizedText();
            }
            Wrapper.mc.textRenderer.draw(new MatrixStack(), keyString,  button.frame.getX() + button.frame.getWidth() - 5 - Wrapper.mc.textRenderer.getWidth(keyString), button.frame.getY() + offset + 3, -1);
        }
    }

    public boolean isHovered(final double x, final double y) {
        return x > this.x && x < this.x + 88 && y > this.y && y < this.y + 16;
    }
}
