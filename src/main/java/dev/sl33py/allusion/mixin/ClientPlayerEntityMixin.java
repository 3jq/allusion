package dev.sl33py.allusion.mixin;

import dev.sl33py.allusion.Allusion;
import dev.sl33py.allusion.client.module.Module;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ClientPlayerEntity.class)
public class ClientPlayerEntityMixin {

    @Inject(at = @At("RETURN"), method = "tick()V", cancellable = true)
    public void tick(CallbackInfo info) {
        MinecraftClient mc = MinecraftClient.getInstance();
        Allusion.moduleManager.modules.forEach((k, m) -> {
            if (m.isEnabled() && mc.player != null && mc.world != null) m.onUpdate();
        });
    }
}
