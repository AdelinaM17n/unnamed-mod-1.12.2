package net.fabricmc.example.mixin;

import net.fabricmc.example.IGameOptions;
import net.fabricmc.example.gui.CutefulModScreen;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.options.GameOptions;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(MinecraftClient.class)
public abstract class MinecraftClientMixin {

    @Final
    @Shadow
    public
    GameOptions options;

    @Shadow public abstract void setScreen(Screen screen);

    @Inject(
            method = "handleInputEvents",
            at = @At(
                    value = "HEAD"
            )
    )
    private void handleCutefulModMenuKeybind(CallbackInfo ci) {
        while (((IGameOptions) options).getCutefulModMenu().wasPressed()) {
            setScreen(new CutefulModScreen());
        }
    }
}