package dev.sl33py.allusion.client.ui.clickgui;

import net.minecraft.client.MinecraftClient;

public class Component {

    public final static MinecraftClient mc;

    public void render() { }

    public void updateComponent(double mouseX, double mouseY) { }

    public void mouseClicked(double mouseX, double mouseY, int button) { }

    public void mouseReleased( double mouseX,  double mouseY,  int mouseButton) { }

    public void keyTyped( int key) { }

    public void setOffset( int offset) { }

    public int getHeight() { return 0; }


    static {
        mc = MinecraftClient.getInstance();
    }

}
