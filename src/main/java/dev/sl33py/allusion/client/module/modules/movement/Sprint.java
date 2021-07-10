package dev.sl33py.allusion.client.module.modules.movement;

import dev.sl33py.allusion.client.module.Module;

public class Sprint extends Module {
    public Sprint() {
        super("Sprint", "Sprint", Category.Movement);
        setEnabled(true);
    }

    @Override public void onUpdate() {
        if(mc.player.forwardSpeed != 0f || mc.player.sidewaysSpeed != 0f) mc.player.setSprinting(true);
    }
}
